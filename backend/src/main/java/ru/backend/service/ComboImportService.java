package ru.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.model.*;
import ru.backend.repositories.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ComboImportService {

    private final ComboRepository comboRepository;
    private final ComboSlotRepository comboSlotRepository;
    private final ComboSlotItemRepository comboSlotItemRepository;
    private final ObjectMapper objectMapper;
    private final GeneralMenuRepository generalMenuRepository;

    public ComboImportService(
            ComboRepository comboRepository,
            ComboSlotRepository comboSlotRepository,
            ComboSlotItemRepository comboSlotItemRepository,
            ObjectMapper objectMapper, GeneralMenuRepository generalMenuRepository
    ) {
        this.comboRepository = comboRepository;
        this.comboSlotRepository = comboSlotRepository;
        this.comboSlotItemRepository = comboSlotItemRepository;
        this.objectMapper = objectMapper;
        this.generalMenuRepository = generalMenuRepository;
    }

    @Transactional
    public void importCombosFromJson() throws IOException {
//        File file = new File( "src/main/resources/dodo_combo_menu.json" );
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dodo_combo_menu.json");
        if (inputStream == null) {
            throw new FileNotFoundException("dodo_general_menu.json not found in classpath");
        }

//        List<JsonNode> comboNodes = objectMapper.readValue(file, new TypeReference<>() {});
        List<JsonNode> comboNodes = objectMapper.readValue(inputStream, new TypeReference<>() {});
        for (JsonNode comboNode : comboNodes) {
            Combo combo = new Combo();
            String comboTitle = comboNode.get("title").asText();
            BigDecimal comboPrice = new BigDecimal(comboNode.get("price").asText());
            combo.setTitle(comboTitle);
            combo.setPrice(comboPrice);
            comboRepository.save(combo);

            JsonNode itemsNode = comboNode.get("items");
            for (Iterator<String> it = itemsNode.fieldNames(); it.hasNext(); ) {
                String slotKey = it.next();
                ComboSlot comboSlot = new ComboSlot();
                comboSlot.setCombo(combo);
                comboSlotRepository.save(comboSlot);

                for (JsonNode itemNode : itemsNode.get(slotKey)) {
                    String itemTitle = itemNode.get("title").asText();
                    BigDecimal extraPrice = new BigDecimal(itemNode.get("extra_price").asText());
                    Optional<GeneralMenu> gMenu =  generalMenuRepository.findByTitle(itemTitle);

                    gMenu.ifPresent(generalMenu -> {
                        ComboSlotItem csItem = new ComboSlotItem();
                        csItem.setGeneralMenu( generalMenu );
                        csItem.setComboSlot( comboSlot );
                        csItem.setExtraPrice( extraPrice );
                        comboSlotItemRepository.save( csItem );
                    });
                }
            }
        }
    }
}
