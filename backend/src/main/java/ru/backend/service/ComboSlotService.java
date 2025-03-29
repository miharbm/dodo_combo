package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.ComboSlot;
import ru.backend.repositories.ComboSlotRepository;

@Service
public class ComboSlotService {

    private final ComboSlotRepository comboSlotRepository;

    @Autowired
    public ComboSlotService(ComboSlotRepository comboSlotRepository) {
        this.comboSlotRepository = comboSlotRepository;
    }

    public ComboSlot createComboSlot(ComboSlot comboSlot) {
        return comboSlotRepository.save(comboSlot);
    }
}

