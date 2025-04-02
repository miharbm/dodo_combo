package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "slot_items")
public class ComboSlotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private ComboSlot comboSlot;

    @ManyToOne
    @JoinColumn(name = "gmenu_id", nullable = false)
    private GeneralMenu generalMenu;

    @Column(name = "extra_price", nullable = false)
    private BigDecimal extraPrice;


    @Override
    public String toString() {
        return "SlotItem{" +
                "id=" + id +
                ", generalMenuId=" + generalMenu.getId() +
                ", extraPrice=" + extraPrice +
                '}';
    }
}
