package ru.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "slot_item_relation")
public class SlotItemRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  // Уникальный идентификатор для этой сущности

    @ManyToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private ComboSlot comboSlot;

    @ManyToOne
    @JoinColumn(name = "slot_item_id", nullable = false)
    private SlotItem slotItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComboSlot getComboSlot() {
        return comboSlot;
    }

    public void setComboSlot(ComboSlot comboSlot) {
        this.comboSlot = comboSlot;
    }

    public SlotItem getSlotItem() {
        return slotItem;
    }

    public void setSlotItem(SlotItem slotItem) {
        this.slotItem = slotItem;
    }
}
