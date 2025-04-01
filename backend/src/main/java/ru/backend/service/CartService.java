package ru.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.*;
import ru.backend.repositories.ComboRepository;
import ru.backend.repositories.GeneralMenuRepository;
import ru.backend.repositories.SlotItemRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final ComboRepository comboRepository;
    private final GeneralMenuRepository generalMenuRepository;
    private final SlotItemRepository slotItemRepository;

    @Autowired
    public CartService(ComboRepository comboRepository, GeneralMenuRepository generalMenuRepository, SlotItemRepository slotItemRepository) {
        this.comboRepository = comboRepository;
        this.generalMenuRepository = generalMenuRepository;
        this.slotItemRepository = slotItemRepository;
    }

    public CartResponse processCart(List<CartItemRequest> cartItems) {
        // 1. Загружаем данные
        Map<Long, Integer> cartMap = cartItems.stream()
                .collect( Collectors.toMap(CartItemRequest::getId, CartItemRequest::getCount));

        List<Combo> combos = comboRepository.findAllWithSlotsAndItems();
        List<GeneralMenu> menuItems = generalMenuRepository.findAllById(cartMap.keySet());
        System.out.println("Загруженные комбо: " + combos);


        // 2. Разбираем корзину по комбо
        List<ComboResult> usedCombos = new ArrayList<>();
        List<StandaloneItem> standaloneItems = new ArrayList<>();

        while (!cartMap.isEmpty()) {
            Optional<ComboResult> bestCombo = findBestCombo(cartMap, combos);

            if (bestCombo.isPresent()) {
                usedCombos.add(bestCombo.get());
                bestCombo.get().getUsedItems().forEach(cartMap::remove);
            } else {
                // Если нет подходящего комбо, оставляем товар отдельно
                long itemId = cartMap.keySet().iterator().next();
                standaloneItems.add(new StandaloneItem(itemId, cartMap.remove(itemId)));
            }
        }

        return new CartResponse(usedCombos, standaloneItems);
    }

    private Optional<ComboResult> findBestCombo(Map<Long, Integer> cart, List<Combo> combos) {
        return combos.stream()
                .map(combo -> calculateComboCost(combo, cart))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .min( Comparator.comparingDouble(ComboResult::getTotalPrice));
    }

    private Optional<ComboResult> calculateComboCost(Combo combo, Map<Long, Integer> cart) {
        Map<Long, SlotItem> bestSlotItems = new HashMap<>();
        BigDecimal totalPrice = new BigDecimal(combo.getPrice()); // Используем BigDecimal для общей цены

        for (ComboSlot slot : combo.getSlots()) {
            Optional<SlotItem> bestItem = slot.getItems().stream()
                    .filter(item -> cart.containsKey(item.getGeneralMenu().getId()))
                    .min(Comparator.comparingDouble(item -> item.getExtraPrice().doubleValue()));  // Преобразуем BigDecimal в double для сравнения

            if (bestItem.isPresent()) {
                SlotItem chosenItem = bestItem.get();
                bestSlotItems.put(chosenItem.getGeneralMenu().getId(), chosenItem);
                totalPrice = totalPrice.add(chosenItem.getExtraPrice());  // Используем метод add для BigDecimal
            } else {
                return Optional.empty(); // Если не хватает товаров для комбо, оно нам не подходит
            }
        }

        return Optional.of(new ComboResult(combo.getId(), bestSlotItems, totalPrice.doubleValue()));
    }

}

