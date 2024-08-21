package com.monetize360.cbook.dao;

import com.monetize360.cbook.domain.Contact;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ContactDao {

    Contact insertContact(Contact contact);
    Contact updateContact(Contact contact);
    Contact getContactById(UUID uuid);
    void deleteContact(UUID uuid);
    List<Contact> getAllContacts(String field, String order, int page, int size);
    List<Contact> searchContacts(String search);

}
