package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.backend.model.Combo;

import java.util.List;
import java.util.Set;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByTitle(String title);

    @Query("""
        SELECT DISTINCT c FROM Combo c
        JOIN c.slots cs
        JOIN cs.items ci
        WHERE ci.generalMenu.id IN :generalMenuIds
    """)
    Set<Combo> findCombosByGeneralMenuIds(@Param("generalMenuIds") List<Long> generalMenuIds);

}

