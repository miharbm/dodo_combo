package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.ComboSlot;
import ru.backend.model.ComboSlotItem;

import java.util.List;
import java.util.Optional;

public interface ComboSlotRepository extends JpaRepository<ComboSlot, Long> {
}

