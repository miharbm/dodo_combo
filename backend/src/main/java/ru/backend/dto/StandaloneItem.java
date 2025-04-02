package ru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StandaloneItem {
    private Long itemId;
    private Integer count;
}
