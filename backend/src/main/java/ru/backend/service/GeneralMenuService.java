package ru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.GeneralMenu;
import ru.backend.repositories.GeneralMenuRepository;

import java.util.List;

@Service
public class GeneralMenuService {

    private final GeneralMenuRepository generalMenuRepository;

    @Autowired
    public GeneralMenuService (GeneralMenuRepository generalMenuRepository) {
        this.generalMenuRepository = generalMenuRepository;
    }

    public List<GeneralMenu> getAllGeneralMenus() {
        return generalMenuRepository.findAll();
    }

    public GeneralMenu getGeneralMenuByCategory(String category) {
        return generalMenuRepository.findByCategory(category);
    }

    public GeneralMenu createGeneralMenu(GeneralMenu generalMenu) {
        return generalMenuRepository.save(generalMenu);
    }
}
