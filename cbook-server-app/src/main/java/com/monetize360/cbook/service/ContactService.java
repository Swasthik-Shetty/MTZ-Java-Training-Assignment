package com.monetize360.cbook.service;

import com.monetize360.cbook.domain.Contact;
import com.monetize360.cbook.dto.ContactDto;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    ContactDto addContact(ContactDto contactDto);
    ContactDto updateContact(ContactDto contactDto);
    ContactDto getContactById(UUID uuid);
    void deleteContact(UUID uuid);
    List<ContactDto> getAllContacts(String field, String direction, int page, int size);
    List<ContactDto> searchContacts(String search);
}
