package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.ComboSlot;
import ru.backend.model.ComboSlotItem;

import java.util.List;
import java.util.Optional;

public interface ComboSlotItemRepository extends JpaRepository<ComboSlotItem, Long> {
//    Optional<ComboSlotItem> findByTitle(String title);
    Optional<List<ComboSlotItem>> findByComboSlotAndGeneralMenuIdIn(ComboSlot comboSlot, List<Long> generalMenuIds);
}


