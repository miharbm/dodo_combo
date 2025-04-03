package ru.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.dto.CartItemRequest;
import ru.backend.model.ComboVariant;
import ru.backend.service.CartService;
import ru.backend.service.ComboImportService;
import ru.backend.service.ComboService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/combo")
@CrossOrigin(origins = "*")
public class ComboController {

    private final ComboImportService comboImportService;
    private final ComboService comboService;
    private final CartService cartService;

    // Используем конструктор для внедрения зависимости
    public ComboController(ComboImportService comboImportService, ComboService comboService, CartService cartService) {
        this.comboImportService = comboImportService;
        this.comboService = comboService;
        this.cartService = cartService;
    }

    @PostMapping("/init")
    public ResponseEntity<Void> init() throws IOException {
        comboImportService.importCombosFromJson();
        return ResponseEntity.status( HttpStatus.CREATED).build();
    }

    @PostMapping("pick-up")
    public ResponseEntity<Set<ComboVariant>> processCart(
            @RequestBody List<CartItemRequest> cartItems,
            @RequestParam(defaultValue = "0") int allowedMissingSlots) {
        return ResponseEntity.ok( cartService.findCombos(cartItems, allowedMissingSlots) );
    }

    @GetMapping
    public String getAllCombos() {
//        return comboService.getAllCombos();
        return null;
    }
}
