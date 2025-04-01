package ru.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class CartResponse {
    private List<ComboResult> combos;
    private List<StandaloneItem> standalone;
}

