package ru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.backend.model.ComboSlotItem;

import java.util.Map;

@Data
@AllArgsConstructor
public class ComboResult {
    private Long comboId;
    private Map<Long,ComboSlotItem> usedItems;
    private double totalPrice;
}
