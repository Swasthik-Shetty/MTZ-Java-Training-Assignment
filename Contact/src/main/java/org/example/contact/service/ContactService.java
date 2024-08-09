package org.example.contact.service;

import org.example.contact.dto.ContactDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;

public interface ContactService 
    public void create(Connection conn, ContactDTO contactDTO);
    public void update(Connection conn,ContactDTO contactDTO);
    public List<ContactDTO> search(Connection conn,ContactDTO contactDTO);
    public boolean delete(Connection conn,ContactDTO contactDTO);
    public ContactDTO getById(Connection conn, int id);
    public void importExcel(Connection conn);
    public void exportExcel(Connection conn, ContactDTO contactDTO);
}
