package com.monetize360.cbook.dao;

import com.monetize360.cbook.domain.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactDao {
    Contact insertContact(Contact contact);
    Contact updateContact(Contact contact);
    Contact getContactById(UUID uuid);
    void deleteContact(UUID uuid);
    List<Contact> getAllContacts();
    List<Contact> searchContacts(String search);
}
