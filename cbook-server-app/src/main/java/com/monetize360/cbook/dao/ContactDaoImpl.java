package com.monetize360.cbook.dao;

import com.monetize360.cbook.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Contact insertContact(Contact contact) {
        String sql = "INSERT INTO contacts (id, first_name, last_name, email, mobile, deleted) VALUES (?, ?, ?, ?, ?, ?)";
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, contact.getFirst_name(), contact.getLast_name(), contact.getEmail(), contact.getMobile(), contact.isDeleted());
        contact.setId(id);
        return contact;
    }

    @Override
    public Contact updateContact(Contact contact) {
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, email = ?, mobile = ?, deleted = ? WHERE id = ?";
        jdbcTemplate.update(sql, contact.getFirst_name(), contact.getLast_name(), contact.getEmail(), contact.getMobile(), contact.isDeleted(), contact.getId());
        return contact;
    }

    @Override
    public Contact getContactById(UUID id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{UUID.fromString(String.valueOf(id))}, (rs, rowNum) -> {
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
    public void deleteContact(UUID id) {
        String sql = "UPDATE contacts SET deleted = true WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Contact> getAllContacts( String field, String order, int page, int size) {
        int offset = Math.max(page*size,0);
        Sort.Direction direction = Sort.Direction.fromOptionalString(order.toUpperCase())
                                    .orElse(Sort.Direction.ASC);
        String sql = "SELECT * FROM contacts where deleted = false ORDER BY " + field + " " + direction + " LIMIT " + size + " OFFSET " + offset;

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
        String sql = "SELECT * FROM contacts WHERE (first_name LIKE ? OR last_name LIKE ? OR email LIKE ? OR mobile LIKE ?) AND deleted = false";
        String query = "%" + search + "%";
        return jdbcTemplate.query(sql, new Object[]{query, query, query, query}, (rs, rowNum) -> {
            Contact contact = new Contact();
            contact.setId(UUID.fromString(rs.getString("id")));
            contact.setFirst_name(rs.getString("first_name"));
            contact.setLast_name(rs.getString("last_name"));
            contact.setMobile(rs.getString("mobile"));
            contact.setEmail(rs.getString("email"));
            contact.setDeleted(rs.getBoolean("deleted"));
            return contact;
        });
    }


}
