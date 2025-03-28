package ru.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.service.ComboImportService;

import java.io.IOException;

@RestController
@RequestMapping("/combo")
public class ComboController {

    private final ComboImportService comboImportService;

    // Используем конструктор для внедрения зависимости
    public ComboController(ComboImportService comboImportService) {
        this.comboImportService = comboImportService;
    }

    @PostMapping("/init")
    public ResponseEntity<Void> init() throws IOException {
        comboImportService.importCombosFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();

    }
}
