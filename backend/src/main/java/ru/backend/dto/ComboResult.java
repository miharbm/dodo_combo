package ru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.backend.model.Combo;
import ru.backend.model.ComboSlot;
import ru.backend.model.ComboSlotItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ComboResult {
    private final Combo combo;
    private List<ComboSlotItem> usedItems; // Использованные элементы
    private List<ComboSlot> missingSlots; // Недостающие элементы
    private BigDecimal finalComboPrice; // Общая цена комбо

    public ComboResult(Combo combo) {
        this.combo = combo;
        this.usedItems = new ArrayList<>(); // Инициализация пустого списка
        this.missingSlots = new ArrayList<>(); // Инициализация пустого списка
        this.finalComboPrice = BigDecimal.ZERO;
    }

    public void increaseFinalComboPrice(BigDecimal amountToAdd) {
        this.finalComboPrice = this.finalComboPrice.add( amountToAdd );
    }
}