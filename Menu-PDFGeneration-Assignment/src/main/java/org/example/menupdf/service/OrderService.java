package org.example.menupdf.service;

import org.example.menupdf.domain.Menu;
import org.example.menupdf.dto.OrderDTO;
import org.example.menupdf.dto.OrderInfoDTO;

import java.util.List;

public interface OrderService {
    public List<OrderDTO> processOrder(Menu menu);
    public OrderInfoDTO generateInvoice(List<OrderDTO> orders, String name);
}
