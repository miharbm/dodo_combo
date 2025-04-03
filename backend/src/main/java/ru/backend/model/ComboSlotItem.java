package ru.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private ComboSlot comboSlot;

    @ManyToOne
    @JoinColumn(name = "gmenu_id", nullable = false)
    @JsonIgnore
    private GeneralMenu generalMenu;

    @Column(name = "extra_price", nullable = false)
    private BigDecimal extraPrice;

    public ComboSlotItem(Long id, BigDecimal add) {
        this.id = id;
        this.extraPrice = add;
    }

    public ComboSlotItem() {

    }


    @Override
    public String toString() {
        return "SlotItem{" +
                "id=" + id +
                ", generalMenuId=" + generalMenu.getId() +
                ", extraPrice=" + extraPrice +
                '}';
    }

    public Long getSlotId() {
        return comboSlot.getId();
    }

    public Long getGmenuId() {
        return generalMenu.getId();
    }
}
