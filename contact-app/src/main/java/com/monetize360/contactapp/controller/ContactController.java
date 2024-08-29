package com.monetize360.contactapp.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.contactapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;


    @PostMapping("/import")
    public String importUsers(@RequestParam("file") MultipartFile file) {
        try {
            contactService.importExcelData(file);
            return "File imported successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error importing file: " + e.getMessage();
        }
    }
}
