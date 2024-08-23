package com.monetize360.cbook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.monetize360.cbook.dao.ContactRepository;
import com.monetize360.cbook.domain.Contact;
import com.monetize360.cbook.dto.ContactDto;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j

public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        log.info("Added contact with ID: {}", savedContact.getId());
        return objectMapper.convertValue(savedContact, ContactDto.class);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        // Convert the DTO to the entity
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);

        // Fetch the existing contact from the database using its ID
        Optional<Contact> existingContactOptional = contactRepository.findById(contact.getId());
        if (existingContactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + contact.getId());
        }

        // Merge the new data with the existing entity
        Contact existingContact = existingContactOptional.get();
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setMobile(contact.getMobile());
        // Add more fields as needed

        // Save the updated contact
        Contact updatedContact = contactRepository.save(existingContact);

        // Log the updated contact ID
        log.info("Updated contact with ID: {}", updatedContact.getId());

        // Convert the updated entity back to the DTO
        return objectMapper.convertValue(updatedContact, ContactDto.class);

    }

    @Override
    public ContactDto getContactById(UUID uuid) {


        return contactRepository.findById(uuid)
                .map(contact -> objectMapper.convertValue(contact, ContactDto.class))
                .orElse(null);
    }

    @Override
    public void deleteContact(UUID id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + id);
        }

        // Set the 'deleted' flag to true for soft deletion
        Contact contact = contactOptional.get();
        contact.setDeleted(true);

        // Save the updated entity back to the database
        contactRepository.save(contact);

        // Log the deletion
        log.info("Deleted contact with ID: {}", id);
    }

    @Override
    public List<ContactDto> getAllContacts(String field, String direction, int page, int size) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        // Create a PageRequest object for pagination and sorting
        Pageable pageable = PageRequest.of(page, size, sort);

        // Fetch paginated and sorted contacts using the repository
        Page<Contact> contactPage = contactRepository.findAll(pageable);

        // Log the contact count
        log.info("Contact count size is: {}", contactPage.getTotalElements());

        // Convert the paginated list of entities to DTOs
        return objectMapper.convertValue(contactPage.getContent(), new TypeReference<List<ContactDto>>() {});
    }

    @Override
    public List<ContactDto> searchContacts(String search) {
        List<Contact> contacts = contactRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrMobileContainingIgnoreCase(
                    search,search,search,search);

        // Log the result count
        log.info("Search result count size is: {}", contacts.size());

        // Convert the entity list to DTO list
        return objectMapper.convertValue(contacts, new TypeReference<List<ContactDto>>() {});
    }

}
