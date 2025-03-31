package ru.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.Combo;
import ru.backend.service.ComboImportService;
import ru.backend.service.ComboService;

import java.io.IOException;

@RestController
@RequestMapping("/combo")
@CrossOrigin(origins = "*")
public class ComboController {

    private final ComboImportService comboImportService;
    private final ComboService comboService;

    // Используем конструктор для внедрения зависимости
    public ComboController(ComboImportService comboImportService, ComboService comboService) {
        this.comboImportService = comboImportService;
        this.comboService = comboService;
    }

    @PostMapping("/init")
    public ResponseEntity<Void> init() throws IOException {
        comboImportService.importCombosFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();
    }

    @PostMapping("pick-up")
    public ResponseEntity<Combo> pickUpCombo() {
        return ResponseEntity.status( HttpStatus.FORBIDDEN).build();

    }

    @GetMapping
    public String getAllCombos() {
//        return comboService.getAllCombos();
        return null;
    }
}
