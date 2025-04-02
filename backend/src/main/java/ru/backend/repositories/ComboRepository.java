package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.backend.model.Combo;

import java.util.List;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByTitle(String title);

}

