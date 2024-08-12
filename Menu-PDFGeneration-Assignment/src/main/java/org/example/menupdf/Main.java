package org.example.menupdf;

import org.example.menupdf.dto.OrderDTO;
import org.example.menupdf.dto.OrderInfoDTO;
import org.example.menupdf.service.OrderService;
import org.example.menupdf.service.OrderServiceImpl;
import org.example.menupdf.service.PdfGenerationService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = sc.nextLine();
        OrderService orderService = new OrderServiceImpl();
        System.out.println("Hi "+ name + " Welcome to Hotel!!");
        List<OrderDTO> orders = orderService.processOrder();
        OrderInfoDTO orderInfoDTO = orderService.generateInvoice(orders,name);
        PdfGenerationService pdfGenerationService = new PdfGenerationService();
        pdfGenerationService.DTOToXml(orderInfoDTO);
        pdfGenerationService.createPdf();


    }
}
