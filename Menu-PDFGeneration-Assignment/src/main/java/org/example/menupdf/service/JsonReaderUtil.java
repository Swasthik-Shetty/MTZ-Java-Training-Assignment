package org.example.menupdf.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.menupdf.domain.Dessert;
import org.example.menupdf.domain.Drinks;
import org.example.menupdf.domain.Menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReaderUtil {
    private JsonReaderUtil(){}
    public static Menu readTeams() {
       Menu menu = new Menu();
        try{

            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = JsonReaderUtil.class.getClassLoader().getResourceAsStream("menu.json");
            assert inputStream != null;
            JsonNode rootNode = objectMapper.readTree(new InputStreamReader(inputStream));
            JsonNode dessertsNode = rootNode.path("menu").path("desserts");
            JsonNode drinksNode = rootNode.path("menu").path("drinks");


            List<Dessert> desserts = objectMapper.convertValue(dessertsNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Dessert.class));
            for (int i = 0; i < desserts.size(); i++) {
                Dessert dessert = desserts.get(i);
                dessert.setId(i + 1);
            }
            List<Drinks> drinks = objectMapper.convertValue(drinksNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Drinks.class));
            for (int i = 0; i < drinks.size(); i++) {
                Drinks drink = drinks.get(i);
                drink.setId(i + 1 + desserts.size());
            }

            menu.setDesserts(desserts);
            menu.setDrinks(drinks);



        }catch (IOException e)
        { e.printStackTrace();
            System.out.println("IOException occured");
        }
        return menu;
    }
}
