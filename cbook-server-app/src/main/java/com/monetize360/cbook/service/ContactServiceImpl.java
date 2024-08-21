package com.monetize360.cbook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.cbook.dao.ContactDao;
import com.monetize360.cbook.domain.Contact;
import com.monetize360.cbook.dto.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactDao contactDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);
        Contact savedContact = contactDao.insertContact(contact);
        log.info("Added contact with ID: {}", savedContact.getId());
        return objectMapper.convertValue(savedContact, ContactDto.class);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);
        Contact updatedContact = contactDao.updateContact(contact);
        log.info("Updated contact with ID: {}", updatedContact.getId());
        return objectMapper.convertValue(updatedContact, ContactDto.class);
    }

    @Override
    public ContactDto getContactById(UUID uuid) {
        Contact contact = contactDao.getContactById(uuid);
        log.info("Fetched contact with ID: {}", contact.getId());
        return objectMapper.convertValue(contact, ContactDto.class);
    }

    @Override
    public void deleteContact(UUID id) {
        contactDao.deleteContact(id);
        log.info("Deleted contact with ID: {}", id);
    }

    @Override
    public List<ContactDto> getAllContacts(String field, String direction, int page, int size) {
        List<Contact> contacts = contactDao.getAllContacts(field, direction, page, size);
        log.info("Contact count size is : {}", contacts);
        return objectMapper.convertValue(contacts, new TypeReference<List<ContactDto>>() {});
    }

    @Override
    public List<ContactDto> searchContacts(String search) {
        List<Contact> contacts = contactDao.searchContacts(search);
        log.info("Search result count size is: {}", contacts.size());
        return objectMapper.convertValue(contacts, new TypeReference<List<ContactDto>>() {});
    }

}
