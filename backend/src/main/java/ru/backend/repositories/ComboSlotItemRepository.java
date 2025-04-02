package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.ComboSlotItem;

import java.util.Optional;

public interface ComboSlotItemRepository extends JpaRepository<ComboSlotItem, Long> {
//    Optional<ComboSlotItem> findByTitle(String title);
}


