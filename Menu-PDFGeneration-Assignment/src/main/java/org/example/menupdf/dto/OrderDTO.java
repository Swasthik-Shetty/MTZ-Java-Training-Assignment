package org.example.menupdf.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

@JacksonXmlRootElement(localName = "order")
public class OrderDTO {
    private int id;
    private String itemName;
    private float itemPrice;
    private int quantity;
    private float totalPrice;
}
