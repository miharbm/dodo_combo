package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.ComboSlotItem;
import ru.backend.repositories.ComboSlotItemRepository;

@Service
public class SlotItemService {

    private final ComboSlotItemRepository comboSlotItemRepository;

    @Autowired
    public SlotItemService(ComboSlotItemRepository comboSlotItemRepository) {
        this.comboSlotItemRepository = comboSlotItemRepository;
    }

    public ComboSlotItem createSlotItem(ComboSlotItem comboSlotItem) {
        return comboSlotItemRepository.save( comboSlotItem );
    }
}
