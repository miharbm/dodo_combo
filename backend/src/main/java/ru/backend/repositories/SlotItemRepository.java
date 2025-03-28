package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.SlotItem;

import java.util.Optional;

public interface SlotItemRepository extends JpaRepository<SlotItem, Long> {
    Optional<SlotItem> findByTitle(String title);
}


