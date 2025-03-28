package ru.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "slot_items")
public class SlotItem {

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

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GeneralMenu getGeneralMenu() {
        return generalMenu;
    }

    public void setGeneralMenu(GeneralMenu generalMenu) {
        this.generalMenu = generalMenu;
    }

    public BigDecimal getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(BigDecimal extraPrice) {
        this.extraPrice = extraPrice;
    }

    public List<SlotItemRelation> getRelations() {
        return relations;
    }

    public void setRelations(List<SlotItemRelation> relations) {
        this.relations = relations;
    }
}
