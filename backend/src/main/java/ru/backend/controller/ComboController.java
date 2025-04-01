package ru.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.CartItemRequest;
import ru.backend.model.CartResponse;
import ru.backend.model.Combo;
import ru.backend.service.CartService;
import ru.backend.service.ComboImportService;
import ru.backend.service.ComboService;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<CartResponse> processCart(@RequestBody List<CartItemRequest> cartItems) {
        return ResponseEntity.ok(cartService.processCart(cartItems));
    }

    @GetMapping
    public String getAllCombos() {
//        return comboService.getAllCombos();
        return null;
    }
}
