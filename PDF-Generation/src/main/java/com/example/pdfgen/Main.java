package com.example.pdfgen;

import com.example.pdfgen.service.PdfGenerationServiceImpl;

public class Main {
    public static void main(String[] args) {
        PdfGenerationServiceImpl pdfService = new PdfGenerationServiceImpl();
        pdfService.createPdf();
        System.out.println("PDF generated successfully.");
    }
}
