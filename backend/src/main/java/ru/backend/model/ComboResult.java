package ru.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ComboResult {
    private Long comboId;
    private Map<Long, SlotItem> usedItems;
    private double totalPrice;
}
