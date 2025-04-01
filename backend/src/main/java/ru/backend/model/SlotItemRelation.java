package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
