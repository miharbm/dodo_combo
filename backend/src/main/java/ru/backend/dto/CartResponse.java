package ru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CartResponse {
    private List<ComboResult> combos;
    private List<StandaloneItem> standalone;
}

