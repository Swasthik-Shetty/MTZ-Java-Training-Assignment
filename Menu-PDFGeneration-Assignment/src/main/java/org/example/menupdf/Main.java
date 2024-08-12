package org.example.menupdf;

import org.example.menupdf.domain.Dessert;
import org.example.menupdf.domain.Drinks;
import org.example.menupdf.domain.Menu;
import org.example.menupdf.dto.OrderDTO;
import org.example.menupdf.dto.OrderInfoDTO;
import org.example.menupdf.service.JsonReaderUtil;
import org.example.menupdf.service.OrderService;
import org.example.menupdf.service.OrderServiceImpl;
import org.example.menupdf.service.PdfGenerationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Menu menu = JsonReaderUtil.readTeams();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();
        OrderService orderService = new OrderServiceImpl();
        System.out.println("Hi "+ name + " Welcome to Hotel!!");

        List<Dessert> desserts = menu.getDesserts();
        List<Drinks> drinks = menu.getDrinks();
        System.out.println("---------------------------------Menu------------------------------------");

        // Print Desserts section
        System.out.println("-------------------------------Desserts----------------------------------");
        System.out.printf("| %-3s | %-50s | %-10s |\n", "ID", "Name", "Price");
        System.out.println("-------------------------------------------------------------------------");
        for (Dessert dessert : desserts) {
            String dname = String.format("%-50s", dessert.getName());
            System.out.printf("| %-3d | %-50s | %10.2f |\n", dessert.getId(), dname, dessert.getPrice());
        }

        // Print Drinks section
        System.out.println("--------------------------------Drinks-----------------------------------");
        System.out.printf("| %-3s | %-50s | %-10s |\n", "ID", "Name", "Price");
        System.out.println("-------------------------------------------------------------------------");
        for (Drinks drink : drinks) {
            String dkname = String.format("%-50s", drink.getName()); // Pad the name to 50 characters
            System.out.printf("| %-3d | %-50s | %10.2f |\n", drink.getId(), dkname, drink.getPrice());
        }


        System.out.println("------------------------------------------------------------------------");

        System.out.println("Please select the items from above menu:");

        List<OrderDTO> orders = orderService.processOrder(menu);
        OrderInfoDTO orderInfoDTO = orderService.generateInvoice(orders,name);
        PdfGenerationService pdfGenerationService = new PdfGenerationService();
        pdfGenerationService.DTOToXml(orderInfoDTO);
        pdfGenerationService.createPdf();


    }
}
