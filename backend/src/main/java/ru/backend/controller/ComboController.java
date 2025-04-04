package ru.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.backend.dto.CartItemRequest;
import ru.backend.dto.ComboVariant;
import ru.backend.service.CartService;
import ru.backend.service.ImportService;
import ru.backend.service.ComboService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/combo")
@CrossOrigin(origins = "*")
public class ComboController {
    private final ImportService importService;
    private final CartService cartService;

    public ComboController(ImportService importService, ComboService comboService, CartService cartService) {
        this.importService = importService;
        this.cartService = cartService;
    }


    @PostMapping("pick-up")
    public ResponseEntity<Set<ComboVariant>> processCart(
            @RequestBody List<CartItemRequest> cartItems,
            @RequestParam(defaultValue = "0") int allowedMissingSlots) {
        return ResponseEntity.ok( cartService.findCombos(cartItems, allowedMissingSlots) );
    }

}
