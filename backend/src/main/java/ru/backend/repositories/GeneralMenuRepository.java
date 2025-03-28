package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.backend.model.GeneralMenu;

public interface GeneralMenuRepository extends JpaRepository<GeneralMenu, Long> {
    GeneralMenu findByCategory(String category);
    GeneralMenu findByTitle(String title);
}

