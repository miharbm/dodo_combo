package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.Combo;
import ru.backend.repositories.ComboRepository;

@Service
public class ComboService {

    private final ComboRepository comboRepository;

    @Autowired
    public ComboService(ComboRepository comboRepository) {
        this.comboRepository = comboRepository;
    }

    public Combo getComboByTitle(String title) {
        return comboRepository.findByTitle(title);
    }

    public Combo createCombo(Combo combo) {
        return comboRepository.save(combo);
    }
}

