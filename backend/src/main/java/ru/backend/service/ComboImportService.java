package ru.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.model.*;
import ru.backend.repositories.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
public class ComboImportService {

    private final ComboRepository comboRepository;
    private final ComboSlotRepository comboSlotRepository;
    private final SlotItemRepository slotItemRepository;
    private final SlotItemRelationRepository slotItemRelationRepository;
    private final ObjectMapper objectMapper;
    private final GeneralMenuRepository generalMenuRepository;

    public ComboImportService(
            ComboRepository comboRepository,
            ComboSlotRepository comboSlotRepository,
            SlotItemRepository slotItemRepository,
            SlotItemRelationRepository slotItemRelationRepository,
            ObjectMapper objectMapper, GeneralMenuRepository generalMenuRepository
    ) {
        this.comboRepository = comboRepository;
        this.comboSlotRepository = comboSlotRepository;
        this.slotItemRepository = slotItemRepository;
        this.slotItemRelationRepository = slotItemRelationRepository;
        this.objectMapper = objectMapper;
        this.generalMenuRepository = generalMenuRepository;
    }

    @Transactional
    public void importCombosFromJson() throws IOException {
        File file = new File( "src/main/resources/dodo_combo_menu.json" );

        List<JsonNode> comboNodes = objectMapper.readValue(file, new TypeReference<List<JsonNode>>() {});

        for (JsonNode comboNode : comboNodes) {
            Combo combo = new Combo();
            combo.setTitle(comboNode.get("title").asText());
            combo.setPrice(comboNode.get("price").asLong());
            comboRepository.save(combo);

            JsonNode itemsNode = comboNode.get("items");
            for (Iterator<String> it = itemsNode.fieldNames(); it.hasNext(); ) {
                String slotKey = it.next();
                ComboSlot slot = new ComboSlot();
                slot.setCombo(combo);
                comboSlotRepository.save(slot);

                for (JsonNode itemNode : itemsNode.get(slotKey)) {
                    String itemTitle = itemNode.get("title").asText();
                    long extraPrice = itemNode.get("extra_price").asLong(); // Long вместо BigDecimal

                    SlotItem item = slotItemRepository.findByTitle(itemTitle)
                            .orElseGet(() -> {
                                SlotItem newItem = new SlotItem();
                                newItem.setTitle(itemTitle); // Нужно добавить поле title в SlotItem
                                newItem.setExtraPrice(BigDecimal.valueOf(extraPrice));

                                // Находим GeneralMenu, например, по категории
                                GeneralMenu generalMenu = generalMenuRepository.findByCategory("default_category");  // можно изменить логику поиска
                                if (generalMenu == null) {
                                    // Если GeneralMenu не найден, можно создать новый, если нужно
                                    generalMenu = new GeneralMenu();
                                    generalMenu.setCategory("default_category");
                                    generalMenu.setTitle("Default Menu");
                                    generalMenuRepository.save(generalMenu);
                                }
                                newItem.setGeneralMenu(generalMenu); // Устанавливаем найденный GeneralMenu

                                return slotItemRepository.save(newItem);
                            });
                    SlotItemRelation relation = new SlotItemRelation();
                    relation.setComboSlot(slot);
                    relation.setSlotItem(item);
                    slotItemRelationRepository.save(relation); // Убрали setExtraPrice, т.к. его нет в SlotItemRelation
                }
            }
        }
    }
}
