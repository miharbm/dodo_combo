package ru.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.dto.CartItemRequest;
import ru.backend.dto.CartResponse;
import ru.backend.dto.ComboResult;
import ru.backend.dto.StandaloneItem;
import ru.backend.model.*;
import ru.backend.repositories.ComboRepository;
import ru.backend.repositories.GeneralMenuRepository;
import ru.backend.repositories.ComboSlotItemRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final ComboRepository comboRepository;
    private final GeneralMenuRepository generalMenuRepository;
    private final ComboSlotItemRepository comboSlotItemRepository;

    @Autowired
    public CartService(ComboRepository comboRepository, GeneralMenuRepository generalMenuRepository, ComboSlotItemRepository comboSlotItemRepository) {
        this.comboRepository = comboRepository;
        this.generalMenuRepository = generalMenuRepository;
        this.comboSlotItemRepository = comboSlotItemRepository;
    }

//    public CartResponse processCart(List<CartItemRequest> cartItems) {
//        // 1. Загружаем данные
//        Map<Long, Integer> cartMap = cartItems.stream()
//                .collect(Collectors.toMap(CartItemRequest::getId, CartItemRequest::getCount));
//
//        List<Combo> combos = comboRepository.findAllWithSlotsAndItems();
//        List<GeneralMenu> menuItems = generalMenuRepository.findAllById(cartMap.keySet());
//        System.out.println("Загруженные комбо: " + combos);  // Логирование всех загруженных комбо
//
//        // 2. Разбираем корзину по всем возможным комбо
//        List<ComboResult> usedCombos = new ArrayList<>();
//        List<StandaloneItem> standaloneItems = new ArrayList<>();
//
//        // Цикл, который будет продолжаться, пока в корзине есть товары
//        while (!cartMap.isEmpty()) {
//            // Ищем все возможные комбо для оставшихся товаров
//            List<ComboResult> possibleCombos = findPossibleCombos(cartMap, combos);
//
//            // Логирование найденных комбо
//            System.out.println("Возможные комбо: " + possibleCombos);
//
//            if (!possibleCombos.isEmpty()) {
//                // Сортируем по стоимости (по убыванию, чтобы сначала выбрать самые выгодные)
//                possibleCombos.sort(Comparator.comparingDouble(ComboResult::getTotalPrice));
//                ComboResult bestCombo = possibleCombos.get(0);
//
//                // Логируем выбранное комбо
//                System.out.println("Выбранное комбо: " + bestCombo);
//
//                // Добавляем лучшее комбо в список
//                usedCombos.add(bestCombo);
//                bestCombo.getUsedItems().forEach(cartMap::remove);
//            } else {
//                // Если нет подходящих комбо, оставляем товар отдельно
//                long itemId = cartMap.keySet().iterator().next();
//                standaloneItems.add(new StandaloneItem(itemId, cartMap.remove(itemId)));
//            }
//        }
//
//        return new CartResponse(usedCombos, standaloneItems);
//    }

//    private List<ComboResult> findPossibleCombos(Map<Long, Integer> cart, List<Combo> combos) {
//        List<ComboResult> possibleCombos = new ArrayList<>();
//
//        for (Combo combo : combos) {
//            Optional<ComboResult> comboResult = calculateComboCost(combo, cart);
//
//            // Логируем результаты для каждого комбо
//            comboResult.ifPresent(result -> System.out.println("Найдено подходящее комбо: " + result));
//
//            comboResult.ifPresent(possibleCombos::add);
//        }
//
//        return possibleCombos;
//    }

//    private Optional<ComboResult> calculateComboCost(Combo combo, Map<Long, Integer> cart) {
//        Map<Long,ComboSlotItem> chosenItems = new HashMap<>();
//        BigDecimal totalPrice = new BigDecimal(combo.getPrice());
//
//        System.out.println("=== Обрабатываем комбо: " + combo.getTitle() + " ===");
//        System.out.println("Текущая корзина: " + cart);
//
//        Set<ComboSlot> comboSlots = combo.getSlots();
//        System.out.println(comboSlots);
//        for (ComboSlot comboSlot: comboSlots) {
//            List<ComboSlotItem> comboSlotItems = comboSlot.getItems();
//            System.out.println( comboSlotItems );
//        }
//
//        // Список всех доступных товаров
//        List<ComboSlotItem> availableItems = combo.getSlots().stream()
//                .flatMap(slot -> slot.getItems().stream())
//                .peek(item -> System.out.println("Возможный товар: " + item.getGeneralMenu().getId() + " (" + item.getExtraPrice() + ")"))
//                .filter(item -> cart.containsKey(item.getGeneralMenu().getId()))
//                .sorted(Comparator.comparing(item -> item.getExtraPrice().doubleValue())) // Минимизируем доплату
//                .toList();
//
//        System.out.println("Доступные товары в корзине для комбо: " + availableItems);
//
//        // Если товаров недостаточно
//        if (availableItems.size() < combo.getSlots().size()) {
//            System.out.println("Недостаточно товаров для комбо: " + combo.getTitle());
//            return Optional.empty();
//        }
//
//        // Выбираем товары
//        Iterator<ComboSlotItem> itemIterator = availableItems.iterator();
//        for (ComboSlot slot : combo.getSlots()) {
//            if (!itemIterator.hasNext()) {
//                System.out.println("Не хватает товаров для заполнения всех слотов.");
//                return Optional.empty();
//            }
//            ComboSlotItem chosenItem = itemIterator.next();
//            chosenItems.put(chosenItem.getGeneralMenu().getId(), chosenItem);
//            totalPrice = totalPrice.add(chosenItem.getExtraPrice());
//            System.out.println("Выбран товар для слота " + slot.getId() + ": " + chosenItem.getGeneralMenu().getId());
//        }
//
//        System.out.println(">>> Подходит комбо: " + combo.getTitle() + " с итоговой ценой: " + totalPrice);
//        return Optional.of(new ComboResult(combo.getId(), chosenItems, totalPrice.doubleValue()));
//    }

}

