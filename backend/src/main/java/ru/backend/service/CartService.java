package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.backend.dto.CartItemRequest;
import ru.backend.dto.ComboResult;
import ru.backend.dto.ComboVariant;
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

    public Set<ComboVariant> findCombos(List<CartItemRequest> cartItems, int allowedMissingSlots) {

        List<Long> ids = cartItems.stream()
                .map(CartItemRequest::getId)
                .toList();
        Set<Combo> combos = comboRepository.findCombosByGeneralMenuIds( ids );

        List<ComboVariant> comboVariants = new ArrayList<>();

        for (Combo combo : combos) {
            comboVariants.add( processCombo(combos, combo, cartItems, allowedMissingSlots) );
        }

//        Set<ComboVariant> setComboVariants = new HashSet<>(comboVariants);
        Set<ComboVariant> setComboVariants = new TreeSet<>(comboVariants);

//        return comboVariants;
        return setComboVariants;
    }

    private ComboVariant processCombo(Set<Combo> combos, Combo initialCombo, List<CartItemRequest> cartItems, int allowedMissingSlots) {
        List<Long> repeatedIds = cartItems.stream()
                .flatMap(item -> Collections.nCopies(item.getCount(), item.getId()).stream())
                .toList();
        List<Long> mutableRepeatedIds = new ArrayList<>(repeatedIds);

        List<ComboResult> comboResults = new ArrayList<>();


        checkCombo(initialCombo, mutableRepeatedIds, allowedMissingSlots).ifPresent(
            (pair) -> {
                comboResults.add( pair.getFirst() );
                mutableRepeatedIds.clear();
                mutableRepeatedIds.addAll(pair.getSecond());
            }
        );


        AtomicBoolean anotherPass = new AtomicBoolean( true );
        while (anotherPass.get() && mutableRepeatedIds.size() >= 2) {
            anotherPass.set( false );
            for (Combo combo : combos) {
                checkCombo(combo, mutableRepeatedIds, allowedMissingSlots).ifPresent(
                        (pair) -> {
                            comboResults.add( pair.getFirst() );
                            mutableRepeatedIds.clear();
                            mutableRepeatedIds.addAll(pair.getSecond());
                            anotherPass.set( true );
                        }
                );
            }
        }

        List<GeneralMenu> standaloneItems = generalMenuRepository.findByIdIn( mutableRepeatedIds );

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

    private Optional<Pair<ComboResult, List<Long>>> checkCombo(Combo combo, List<Long> mutableRepeatedIds, int allowedMissingSlots ) {
        List<Long> mutableRepeatedIdsCopy = new ArrayList<>(mutableRepeatedIds);
        Set<ComboSlot> slots = combo.getSlots();
        ComboResult comboResult = new ComboResult(combo);
        comboResult.setFinalComboPrice( combo.getPrice() );

        for (ComboSlot slot : slots) {
            List<ComboSlotItem> comboSlotItemList = comboSlotItemRepository.findByComboSlotAndGeneralMenuIdIn(slot, mutableRepeatedIdsCopy);
            if (!comboSlotItemList.isEmpty()) {
                ComboSlotItem comboSlotItem = comboSlotItemList.get( 0 );
                comboResult.getUsedItems().add(comboSlotItem);
                mutableRepeatedIdsCopy.remove(comboSlotItem.getGmenuId());
                comboResult.increaseFinalComboPrice( comboSlotItem.getExtraPrice() );
            } else {
                comboResult.getMissingSlots().add(slot);
            }
        }

        if (comboResult.getMissingSlots().size() <= allowedMissingSlots) {
            return Optional.of(Pair.of(comboResult, mutableRepeatedIdsCopy));
        }

        return Optional.empty();
    }
}