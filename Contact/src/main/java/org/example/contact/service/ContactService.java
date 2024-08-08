package org.example.contact.service;

import org.example.contact.dto.ContactDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.List;

public interface ContactService {
    public void create(Connection conn, ContactDTO contactDTO);
    public ContactDTO update(Connection conn, PreparedStatement stmt,ContactDTO contactDTO);
    public List<ContactDTO> search(Connection conn, PreparedStatement stmt,ContactDTO contactDTO);
    public boolean delete(Connection conn, PreparedStatement stmt,ContactDTO contactDTO);
    public ContactDTO getById(Connection conn, PreparedStatement stmt,ContactDTO contactDTO);
    public void importExcel(Connection conn);
    public boolean exportExcel(Connection conn, PreparedStatement stmt,ContactDTO contactDTO);
}
