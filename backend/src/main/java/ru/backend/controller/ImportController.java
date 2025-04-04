package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.backend.service.DatabaseCleanupService;
import ru.backend.service.ImportService;

import java.io.IOException;

@RestController
@RequestMapping("/import")
@CrossOrigin(origins = "*")
public class ImportController {
    private final ImportService importService;
    private final DatabaseCleanupService databaseCleanupService;

    @Autowired
    public ImportController(ImportService importService, DatabaseCleanupService databaseCleanupService) {
        this.importService = importService;
        this.databaseCleanupService = databaseCleanupService;
    }


    @PostMapping("/general-menu")
    public ResponseEntity<Void> initGeneralMenu() throws IOException {
        importService.importMenusFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();
    }

    @PostMapping("/combo")
    public ResponseEntity<Void> initCombos() throws IOException {
        importService.importCombosFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();
    }

    @PostMapping("/clean-up-tables")
    public ResponseEntity<Void> cleanUpTables() {
        databaseCleanupService.clearAllTables();
        return ResponseEntity.status( HttpStatus.OK).build();
    }
}
