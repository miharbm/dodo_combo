package ru.backend.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SlotItemRelationId implements Serializable {
    private Long slotId;
    private Long slotItemId;

    // Getters, setters, equals, and hashCode
    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotId) {
        this.slotId = slotId;
    }

    public Long getSlotItemId() {
        return slotItemId;
    }

    public void setSlotItemId(Long slotItemId) {
        this.slotItemId = slotItemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlotItemRelationId that = (SlotItemRelationId) o;

        if (!slotId.equals(that.slotId)) return false;
        return slotItemId.equals(that.slotItemId);
    }

    @Override
    public int hashCode() {
        int result = slotId.hashCode();
        result = 31 * result + slotItemId.hashCode();
        return result;
    }
}
