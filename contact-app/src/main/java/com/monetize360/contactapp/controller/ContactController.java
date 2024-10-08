package com.monetize360.contactapp.controller;


import com.monetize360.contactapp.dto.ContactDto;

import com.monetize360.contactapp.service.ContactService;

import com.monetize360.contactapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contactDto){

        ContactDto createdContact = contactService.addContact(contactDto);
        return new ResponseEntity<>(createdContact,HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactDto){

        ContactDto updatedContact = contactService.updateContact(contactDto);
        return new ResponseEntity<>(updatedContact,HttpStatus.OK);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable("id")UUID id){

        ContactDto contact = contactService.getContactById(id);
        if(contact!=null) {
            return new ResponseEntity<>(contact, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ContactDto> deleteContact(@PathVariable("id")UUID id){

        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/all/{field}")
    public ResponseEntity<List<ContactDto>> getAllContacts(
            @PathVariable String field,
            @RequestParam(value = "order", defaultValue = "asc", required = false)String order,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){

        List<ContactDto> contacts = contactService.getAllContacts(field, order, page, size);
        return ResponseEntity.ok(contacts);

    }


    @GetMapping(value = "/QRCode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("id") UUID id) throws Exception {
        BufferedImage qrCode = contactService.generateQRCode(id);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }

    @PostMapping("/importExcel")
    public ResponseEntity<String> importContacts(@RequestParam("file") MultipartFile file) {

            contactService.importExcelData(file);
            return ResponseEntity.ok("Excel file uploaded successfully.");
    }

    @PostMapping("/importCsv")
    public ResponseEntity<String> uploadCsvFile(@RequestParam("file") MultipartFile file) {
        contactService.importCsvData(file);
        return ResponseEntity.ok("CSV file uploaded successfully.");
    }

    @GetMapping(value = "/exportExcel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> exportContacts() {
        byte[] fileContent = contactService.exportContacts();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=contacts.xlsx")
                .body(fileContent);
    }

    @GetMapping("/send-email/{id}")
    public ResponseEntity<String> sendEmail(@PathVariable("id") UUID id,@RequestParam(value = "email")String email) {
        try {
            contactService.sendMail(id, email);
            return ResponseEntity.ok("Mail sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Mail submission failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
