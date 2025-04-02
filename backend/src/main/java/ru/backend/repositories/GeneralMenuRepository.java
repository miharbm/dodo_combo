package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.GeneralMenu;

import java.util.Optional;

public interface GeneralMenuRepository extends JpaRepository<GeneralMenu, Long> {
    GeneralMenu findByCategory(String category);
    Optional<GeneralMenu> findByTitle(String title);
}

