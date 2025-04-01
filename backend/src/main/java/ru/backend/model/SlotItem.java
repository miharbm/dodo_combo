package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "slot_items")
public class SlotItem {

    // Геттеры и сеттеры
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "gmenu_id", nullable = false)
    private GeneralMenu generalMenu;

    @Column(name = "extra_price", nullable = false)
    private BigDecimal extraPrice;

    @OneToMany(mappedBy = "slotItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SlotItemRelation> relations;

}
