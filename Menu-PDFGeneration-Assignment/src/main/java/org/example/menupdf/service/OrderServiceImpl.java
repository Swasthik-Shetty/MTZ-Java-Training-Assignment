package org.example.menupdf.service;

import org.example.menupdf.domain.Dessert;
import org.example.menupdf.domain.Drinks;
import org.example.menupdf.domain.Menu;
import org.example.menupdf.dto.OrderDTO;
import org.example.menupdf.dto.OrderInfoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    public List<OrderDTO> processOrder(Menu menu) {


        Scanner scanner = new Scanner(System.in);
        List<OrderDTO> orders = new ArrayList<>();

        while (true) {
            System.out.print("Enter ID (or 'done' to finish): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            int id = Integer.parseInt(input);

            System.out.print("Enter Quantity: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            // Find the item in desserts or drinks
            Dessert dessert = menu.getDesserts().stream().filter(d -> d.getId() == id).findFirst().orElse(null);
            Drinks drink = menu.getDrinks().stream().filter(d -> d.getId() == id).findFirst().orElse(null);

            if (dessert != null) {
                orders.add(createOrderDTO(dessert.getId(), dessert.getName(), quantity, dessert.getPrice()));
            } else if (drink != null) {
                orders.add(createOrderDTO(drink.getId(), drink.getName(), quantity, drink.getPrice()));
            } else {
                System.out.println("ID not found.");
            }
        }

        return orders;
    }

    private static OrderDTO createOrderDTO(int id, String name, int quantity, float price) {
        float totalPrice = price * quantity;
        return new OrderDTO(id, name, price, quantity, totalPrice);
    }

    public OrderInfoDTO generateInvoice(List<OrderDTO> orders, String name) {

        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setCustomerName(name);
        orderInfoDTO.setOrderDTOList(orders);
        float totalAmt = orders.stream()
                .map(OrderDTO::getTotalPrice)
                .reduce(0.0f, Float::sum);
        orderInfoDTO.setCompleteTotal(totalAmt);
        orderInfoDTO.setTotalWithGST((float) (totalAmt*0.05));
        return orderInfoDTO;
    }


}
