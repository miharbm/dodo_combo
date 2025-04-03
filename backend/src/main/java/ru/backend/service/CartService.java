package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.backend.dto.CartItemRequest;
import ru.backend.dto.ComboResult;
import ru.backend.model.*;
import ru.backend.repositories.ComboRepository;
import ru.backend.repositories.ComboSlotItemRepository;
import ru.backend.repositories.ComboSlotRepository;
import ru.backend.repositories.GeneralMenuRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class CartService {

    private final ComboRepository comboRepository;
    private final ComboSlotRepository comboSlotRepository;
    private final ComboSlotItemRepository comboSlotItemRepository;
    private final GeneralMenuRepository generalMenuRepository;

    @Autowired
    public CartService(ComboRepository comboRepository, ComboSlotRepository comboSlotRepository, ComboSlotItemRepository comboSlotItemRepository, GeneralMenuRepository generalMenuRepository) {
        this.comboRepository = comboRepository;
        this.comboSlotRepository = comboSlotRepository;
        this.comboSlotItemRepository = comboSlotItemRepository;
        this.generalMenuRepository = generalMenuRepository;
    }

    public List<ComboVariant> findCombos(List<CartItemRequest> cartItems) {

        System.out.println(cartItems);
        List<Long> ids = cartItems.stream()
                .map(CartItemRequest::getId)
                .toList();
        Set<Combo> combos = comboRepository.findCombosByGeneralMenuIds( ids );

        List<ComboVariant> comboVariants = new ArrayList<>();

        for (Combo combo : combos) {
            comboVariants.add( processCombo(combos, combo, cartItems) );
        }

        return comboVariants;
    }

    public ComboVariant processCombo(Set<Combo> combos, Combo initialCombo, List<CartItemRequest> cartItems) {
        List<Long> repeatedIds = cartItems.stream()
                .flatMap(item -> Collections.nCopies(item.getCount(), item.getId()).stream())
                .toList();
        List<Long> mutableRepeatedIds = new ArrayList<>(repeatedIds);

        List<ComboResult> comboResults = new ArrayList<>();
//        List<GeneralMenu> standaloneItems = new ArrayList<>();
        System.out.println("standaloneItemsIdsAtInitial" + mutableRepeatedIds);


        checkCombo(initialCombo, mutableRepeatedIds).ifPresent(
            (pair) -> {
                comboResults.add( pair.getFirst() );
                mutableRepeatedIds.retainAll( pair.getSecond() );
            }
        );

        System.out.println("standaloneItemsIdsAfterInitial" + mutableRepeatedIds);


        AtomicBoolean anotherPass = new AtomicBoolean( true );
        while (anotherPass.get() && mutableRepeatedIds.size() >= 2) {
            anotherPass.set( false );
            for (Combo combo : combos) {
                checkCombo(combo, mutableRepeatedIds).ifPresent(
                        (pair) -> {
                            comboResults.add( pair.getFirst() );
                            mutableRepeatedIds.retainAll( pair.getSecond() );
                            anotherPass.set( true );
                        }
                );
                System.out.println("standaloneItemsIds" + mutableRepeatedIds);
            }
        }

//        System.out.println("standaloneItemsIds" + mutableRepeatedIds);
        List<GeneralMenu> standaloneItems = generalMenuRepository.findByIdIn( mutableRepeatedIds );
        System.out.println("standaloneItems" + standaloneItems);

        BigDecimal totalPriceFromCombos = comboResults.stream()
                .map(ComboResult::getFinalComboPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPriceFromStandalone = standaloneItems.stream()
                .map(GeneralMenu::getPrice) // Получаем значение price
                .filter(Objects::nonNull)   // Фильтруем null значения
                .map(BigDecimal::valueOf)   // Преобразуем Long в BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add); // Суммируем

        BigDecimal totalPrice = totalPriceFromCombos.add(totalPriceFromStandalone);

        return new ComboVariant( comboResults, standaloneItems, totalPrice );
    }

    public Optional<Pair<ComboResult, List<Long>>> checkCombo(Combo combo, List<Long> mutableRepeatedIds ) {
        Set<ComboSlot> slots = combo.getSlots();
        ComboResult comboResult = new ComboResult(combo);
        comboResult.setFinalComboPrice( combo.getPrice() );

        for (ComboSlot slot : slots) {
            comboSlotItemRepository.findByComboSlotAndGeneralMenuIdIn(slot, mutableRepeatedIds)
                    .ifPresentOrElse(
                            (comboSlotItemList) -> {
                                if (!comboSlotItemList.isEmpty()) {
                                    ComboSlotItem comboSlotItem = comboSlotItemList.get( 0 );
                                    comboResult.getUsedItems().add(comboSlotItem);
                                    mutableRepeatedIds.remove(comboSlotItem.getGmenuId());
                                    comboResult.increaseFinalComboPrice( comboSlotItem.getExtraPrice() );
                                } else {
                                    comboResult.getMissingSlots().add(slot);
                                }

                            },
                            () -> {
                                comboResult.getMissingSlots().add(slot);
                            }
                    );
        }

        if (comboResult.getMissingSlots().size() <= 1) {
            return Optional.of(Pair.of(comboResult, mutableRepeatedIds));
        }

        return Optional.empty();
    }
}