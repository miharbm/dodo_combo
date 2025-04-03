package ru.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Combo combo;

    @OneToMany(mappedBy = "comboSlot", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComboSlotItem> items = new ArrayList<>();


    @Override
    public String toString() {
        return "ComboSlot{id=" + id + "combo_id'" + combo.getId() + "'}";
    }

    public Long getComboId() {
        return combo.getId();
    }
}
