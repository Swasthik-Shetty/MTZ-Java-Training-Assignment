package com.monetize360.cbook.dao;

import com.monetize360.cbook.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ContactDaoImpl implements ContactDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Contact insertContact(Contact contact) {
        return null;
    }

    @Override
    public Contact updateContact(Contact contact) {
        return null;
    }

    @Override
    public Contact getContactById(UUID uuid) {
        return null;
    }

    @Override
    public void deleteContact(UUID uuid) {

    }

    @Override
    public List<Contact> getAllContacts() {
        String sql = "SELECT * FROM contacts where deleted = false";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Contact contact = new Contact();
            contact.setId(UUID.fromString(rs.getString("id")));
            contact.setFirst_name(rs.getString("first_name"));
            contact.setLast_name(rs.getString("last_name"));
            contact.setEmail(rs.getString("email"));
            contact.setMobile(rs.getString("mobile"));
            contact.setDeleted(rs.getBoolean("deleted"));
            return contact;
        });
    }

    @Override
    public List<Contact> searchContacts(String search) {
        return List.of();
    }
}
