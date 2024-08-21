package com.monetize360.cbook.web;

import com.monetize360.cbook.dto.ContactDto;
import com.monetize360.cbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(createdContact);
    }

    @PutMapping("/update")
    public ResponseEntity<ContactDto> updateContact(@RequestBody ContactDto contactDto){
        ContactDto updatedContact = contactService.updateContact(contactDto);
        return ResponseEntity.ok(updatedContact);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable("id")UUID id){
        ContactDto contact = contactService.getContactById(id);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id")UUID id){
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all/{field}")
    public ResponseEntity<List<ContactDto>> getAllContacts(
            @PathVariable String field,
            @RequestParam(value = "order", defaultValue = "asc", required = false)String order,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){
        List<ContactDto> contacts = contactService.getAllContacts(field,order,page,size);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ContactDto>> searchContacts(@RequestParam("search")String search){
        List<ContactDto> contacts = contactService.searchContacts(search);
        return ResponseEntity.ok(contacts);
    }



}
