package com.monetize360.cbook.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.monetize360.cbook.dto.ContactDto;
import com.monetize360.cbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

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

    @GetMapping("/search")
    public ResponseEntity<List<ContactDto>> searchContacts(@RequestParam(value = "search")String search){

            List<ContactDto> contacts = contactService.searchContacts(search);
            return ResponseEntity.ok(contacts);

    }

    @GetMapping(value = "/QRCode/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("id") UUID id) throws Exception {
        BufferedImage qrCode = contactService.generateQRCode(id);
        return new ResponseEntity<>(qrCode, HttpStatus.OK);
    }



}
