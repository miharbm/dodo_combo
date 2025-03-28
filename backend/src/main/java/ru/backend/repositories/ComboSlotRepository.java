package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.ComboSlot;

public interface ComboSlotRepository extends JpaRepository<ComboSlot, Long> {
}

