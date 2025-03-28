package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.SlotItem;

public interface SlotItemRepository extends JpaRepository<SlotItem, Long> {
}


