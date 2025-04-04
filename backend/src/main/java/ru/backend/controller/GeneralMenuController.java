package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.GeneralMenu;
import ru.backend.service.GeneralMenuService;

import java.util.List;

@RestController
@RequestMapping("/general-menu")
@CrossOrigin(origins = "*")
public class GeneralMenuController {

    private final GeneralMenuService generalMenuService;

    @Autowired
    public GeneralMenuController(GeneralMenuService generalMenuService) {
        this.generalMenuService = generalMenuService;
    }

    @GetMapping
    public List<GeneralMenu> getAllGeneralMenus() {
        return generalMenuService.getAllGeneralMenus();
    }
}

