package com.monetize360.cbook.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.cbook.dao.ContactDao;
import com.monetize360.cbook.domain.Contact;
import com.monetize360.cbook.dto.ContactDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {
        return null;
    }

    @Override
    public ContactDto getContactById(UUID uuid) {
        return null;
    }

    @Override
    public void deleteContact(UUID uuid) {

    }

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactDao.getAllContacts();
        log.info("Contact count size is : {}", contacts);
        return objectMapper.convertValue(contacts, new TypeReference<List<ContactDto>>() {});
    }

    @Override
    public List<ContactDto> searchContacts(String search) {
        return List.of();
    }
}
