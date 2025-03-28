package ru.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "slot_items")
public class SlotItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gmenu_id", nullable = false)
    private GeneralMenu generalMenu;

    private Integer extraPrice;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setSlotId(Long id) {
        this.id = id;
    }

    public GeneralMenu getGeneralMenu() {
        return generalMenu;
    }

    public void setGeneralMenu(GeneralMenu generalMenu) {
        this.generalMenu = generalMenu;
    }

    public Integer getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(Integer extraPrice) {
        this.extraPrice = extraPrice;
    }
}
