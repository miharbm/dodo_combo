package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.Combo;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByTitle(String title);
}

