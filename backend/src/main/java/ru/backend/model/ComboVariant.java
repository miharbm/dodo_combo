package ru.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.backend.dto.ComboResult;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ComboVariant {
    List<ComboResult> comboResults;
    List<GeneralMenu> standaloneItems;
    BigDecimal price;
}
