package org.example.menupdf.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter


public class Menu {
    @JsonProperty("desserts")
    List<Dessert> desserts;
    @JsonProperty("drinks")
    List<Drinks> drinks;

}
