package ru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.backend.model.GeneralMenu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class ComboVariant implements Comparable<ComboVariant> {
    List<ComboResult> comboResults;
    List<GeneralMenu> standaloneItems;
    BigDecimal price;

    @Override
    public int hashCode() {
        // Хэш-код на основе отсортированных ID комбо
        return comboResults.stream()
                .map(comboResult -> comboResult.getCombo().getId())
                .sorted()
                .toList()
                .hashCode();
    }

    @Override
    public int compareTo(ComboVariant other) {
        // Сравниваем по полю price
        return this.price.compareTo(other.price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComboVariant that = (ComboVariant) o;

        // Сравниваем отсортированные ID комбо
        List<Long> thisComboIds = this.comboResults.stream()
                .map(comboResult -> comboResult.getCombo().getId())
                .sorted()
                .toList();

        List<Long> thatComboIds = that.comboResults.stream()
                .map(comboResult -> comboResult.getCombo().getId())
                .sorted()
                .toList();

        return Objects.equals(thisComboIds, thatComboIds);
    }
}
