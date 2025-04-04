package ru.backend.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.repositories.ComboRepository;
import ru.backend.repositories.ComboSlotItemRepository;
import ru.backend.repositories.ComboSlotRepository;
import ru.backend.repositories.GeneralMenuRepository;

@Service
public class DatabaseCleanupService {

    private final ComboRepository comboRepository;
    private final ComboSlotRepository comboSlotRepository;
    private final GeneralMenuRepository generalMenuRepository;
    private final ComboSlotItemRepository comboSlotItemRepository;

    @Autowired
    public DatabaseCleanupService(ComboRepository comboRepository,
                                  ComboSlotRepository comboSlotRepository,
                                  GeneralMenuRepository generalMenuRepository,
                                  ComboSlotItemRepository comboSlotItemRepository) {
        this.comboRepository = comboRepository;
        this.comboSlotRepository = comboSlotRepository;
        this.generalMenuRepository = generalMenuRepository;
        this.comboSlotItemRepository = comboSlotItemRepository;
    }

    @Transactional
    public void clearAllTables() {
        comboRepository.deleteAll();
        comboSlotRepository.deleteAll();
        generalMenuRepository.deleteAll();
        comboSlotItemRepository.deleteAll();
    }
}
