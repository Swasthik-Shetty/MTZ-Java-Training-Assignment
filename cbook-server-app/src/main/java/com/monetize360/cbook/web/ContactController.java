package com.monetize360.cbook.web;

import com.monetize360.cbook.dto.ContactDto;
import com.monetize360.cbook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity<?> addContact(@RequestBody ContactDto contactDto){
        try {
            ContactDto createdContact = contactService.addContact(contactDto);
            return new ResponseEntity<>(createdContact,HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Failed to create contact ", HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateContact(@RequestBody ContactDto contactDto){
        try {
            ContactDto updatedContact = contactService.updateContact(contactDto);
            return new ResponseEntity<>(updatedContact,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("Failed to update", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get/{id}")
    @ExceptionHandler()
    public ResponseEntity<?> getContact(@PathVariable("id")UUID id){

        try {
            ContactDto contact = contactService.getContactById(id);

            return new ResponseEntity<>(contact,HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve contact ", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable("id")UUID id){
        try {
            contactService.deleteContact(id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            return new ResponseEntity<>("Failed to retrieve contact ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/{field}")
    public ResponseEntity<?> getAllContacts(
            @PathVariable String field,
            @RequestParam(value = "order", defaultValue = "asc", required = false)String order,
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "10")int size){
        try {
            List<ContactDto> contacts = contactService.getAllContacts(field, order, page, size);
            return ResponseEntity.ok(contacts);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to retrieve contact ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchContacts(@RequestParam("search")String search){
        try {
            List<ContactDto> contacts = contactService.searchContacts(search);
            return ResponseEntity.ok(contacts);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failed to retrieve contact ", HttpStatus.NOT_FOUND);
        }
    }



}
