package ru.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "combo_slots")
@Getter
@Setter
public class ComboSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "combo_id", nullable = false)
    private Combo combo;

//    @OneToMany(mappedBy = "comboSlot", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SlotItemRelation> itemRelations = new ArrayList<>();
//
//    // Метод для получения товаров в слоте
//    public List<SlotItem> getItems() {
//        List<SlotItem> slotItems = new ArrayList<>();
//        for (SlotItemRelation relation : itemRelations) {
//            slotItems.add(relation.getSlotItem());
//        }
//        return slotItems;
//    }

    @Override
    public String toString() {
        return "ComboSlot{id=" + id + "combo_id'" + combo.getId() + "'}";
    }
}
