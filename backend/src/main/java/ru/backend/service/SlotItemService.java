package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.SlotItem;
import ru.backend.repositories.SlotItemRepository;

@Service
public class SlotItemService {

    private final SlotItemRepository slotItemRepository;

    @Autowired
    public SlotItemService(SlotItemRepository slotItemRepository) {
        this.slotItemRepository = slotItemRepository;
    }

    public SlotItem createSlotItem(SlotItem slotItem) {
        return slotItemRepository.save(slotItem);
    }
}
