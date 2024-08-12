package org.example.menupdf.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


public class Dessert {
    @JsonProperty("name")
    private String name;
    @JsonProperty("price")
    private float price;
    private int id;
}
