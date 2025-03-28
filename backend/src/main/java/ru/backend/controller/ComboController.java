package ru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.backend.model.Combo;
import ru.backend.service.ComboService;

@RestController
@RequestMapping("/combo")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @GetMapping("/{title}")
    public Combo getComboByTitle(@PathVariable String title) {
        return comboService.getComboByTitle(title);
    }

    @PostMapping
    public Combo createCombo(@RequestBody Combo combo) {
        return comboService.createCombo(combo);
    }
}

