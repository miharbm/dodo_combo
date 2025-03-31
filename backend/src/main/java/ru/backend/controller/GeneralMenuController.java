package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.GeneralMenu;
import ru.backend.service.GeneralMenuService;
import ru.backend.service.MenuImportService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/general-menu")
@CrossOrigin(origins = "*")
public class GeneralMenuController {

    private final GeneralMenuService generalMenuService;
    private final MenuImportService menuImportService;

    @Autowired
    public GeneralMenuController(GeneralMenuService generalMenuService, MenuImportService menuImportService) {
        this.generalMenuService = generalMenuService;
        this.menuImportService = menuImportService;
    }

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

    @PostMapping("/init")
    public ResponseEntity<Void>  init() throws IOException {
        menuImportService.importMenusFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();
    }
}

