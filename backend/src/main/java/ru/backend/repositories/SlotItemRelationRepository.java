package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.SlotItemRelation;

public interface SlotItemRelationRepository extends JpaRepository<SlotItemRelation, Long> {
}
