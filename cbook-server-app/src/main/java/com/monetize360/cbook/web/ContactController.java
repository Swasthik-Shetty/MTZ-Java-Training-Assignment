package com.monetize360.cbook.web;

import com.monetize360.cbook.dto.ContactDto;
import com.monetize360.cbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ContactDto> addContact(ContactDto contactDto){
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<ContactDto> updateContact(ContactDto contactDto){
        return null;
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDto> getContact(@PathVariable("id")UUID id){
        return null;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id")UUID id){
        return null;
    }
    @GetMapping("/all")
    public ResponseEntity<List<ContactDto>> getAllContacts(){
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/search")
    public ResponseEntity<ContactDto> searchContacts(@PathVariable("search")String search){
        return null;
    }

}
