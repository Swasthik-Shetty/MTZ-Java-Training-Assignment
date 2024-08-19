package org.example.contact.dao;

import org.example.contact.dto.ContactDTO;

import java.sql.Connection;
import java.util.List;

public interface DBService {
    public List<ContactDTO> getAllContacts(Connection conn);
    public boolean create(Connection conn, ContactDTO contactDTO);

    public boolean update(Connection conn, ContactDTO contactDTO, int updateId);

    public ContactDTO search(Connection conn, String name);

    public boolean delete(Connection conn, int id);

    public ContactDTO getById(Connection conn, int id);
}
