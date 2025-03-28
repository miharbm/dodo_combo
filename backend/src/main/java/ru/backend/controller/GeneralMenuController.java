package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.GeneralMenu;
import ru.backend.service.GeneralMenuService;

import java.util.List;

@RestController
@RequestMapping("/general-menu")
public class GeneralMenuController {

    @Autowired
    private GeneralMenuService generalMenuService;

    @GetMapping
    public List<GeneralMenu> getAllGeneralMenus() {
        return generalMenuService.getAllGeneralMenus();
    }

    @GetMapping("/{category}")
    public GeneralMenu getGeneralMenuByCategory(@PathVariable String category) {
        return generalMenuService.getGeneralMenuByCategory(category);
    }

    @PostMapping
    public GeneralMenu createGeneralMenu(@RequestBody GeneralMenu generalMenu) {
        return generalMenuService.createGeneralMenu(generalMenu);
    }
}

