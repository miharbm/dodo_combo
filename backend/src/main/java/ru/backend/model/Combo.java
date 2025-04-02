package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "combo")
public class Combo {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private BigDecimal price;

//    @OneToMany(mappedBy = "combo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<ComboSlot> slots = new ArrayList<>();
//    @OneToMany(mappedBy = "combo",  cascade = CascadeType.ALL)
//    private List<ComboSlot> slots = new ArrayList<>();
    @OneToMany(mappedBy = "combo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ComboSlot> slots = new HashSet<>();

    @Override
    public String toString() {
        return "Combo{id=" + id + ", title='" + title + "'}";
    }

}
