package ru.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.backend.model.GeneralMenu;
import ru.backend.repositories.ComboRepository;
import ru.backend.repositories.GeneralMenuRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuImportService {

    private final GeneralMenuRepository generalMenuRepository;

    @Autowired
    public MenuImportService(ComboRepository comboRepository, GeneralMenuRepository generalMenuRepository) {
        this.generalMenuRepository = generalMenuRepository;
    }

    public void importMenusFromJson()  throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        File file = new File( "src/main/resources/dodo_general_menu.json" );
        JsonNode rootNode = objectMapper.readTree(file);
        List<GeneralMenu> menuItems = new ArrayList<>();

        // Проходимся по каждой категории и ее элементам
        for (JsonNode categoryNode : rootNode) {
            String categoryTitle = categoryNode.get( "title" ).asText(); // Верхний title — категория
            for (JsonNode itemNode : categoryNode.get( "items" )) {
                GeneralMenu menuItem = new GeneralMenu();
                menuItem.setCategory( categoryTitle );
                menuItem.setTitle( itemNode.get( "title" ).asText() );
                menuItem.setPrice( itemNode.get( "price" ).asLong() );
                menuItems.add( menuItem );
            }
        }
        generalMenuRepository.saveAll( menuItems );
    }
}
