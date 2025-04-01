package ru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.backend.model.Combo;

import java.util.List;

public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByTitle(String title);

//    @Query("SELECT c FROM Combo c JOIN FETCH c.slots JOIN FETCH c.slots")
    @Query("""
        SELECT c FROM Combo c
        JOIN FETCH c.slots cs
        JOIN FETCH cs.itemRelations sir
        JOIN FETCH sir.slotItem si
        JOIN FETCH si.generalMenu gm
    """)
    List<Combo> findAllWithSlotsAndItems();
}

