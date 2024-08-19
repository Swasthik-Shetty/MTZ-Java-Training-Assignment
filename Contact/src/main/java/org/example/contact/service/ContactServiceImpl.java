package org.example.contact.service;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.contact.dao.DBServiceImpl;
import org.example.contact.domain.Contact;
import org.example.contact.dto.ContactDTO;
import org.modelmapper.ModelMapper;

import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    DBServiceImpl dbService = new DBServiceImpl();

    @Override
    public List<ContactDTO> getAllContacts(Connection conn) {

        List<ContactDTO> contacts = dbService.getAllContacts(conn);

        return contacts;
    }

    @Override
    public boolean create(Connection conn, ContactDTO contactDTO) {
        return dbService.create(conn, contactDTO);
    }

    @Override
    public boolean update(Connection conn, ContactDTO contactDTO, int updateId) {
        return dbService.update(conn, contactDTO, updateId);
    }

    @Override
    public ContactDTO search(Connection conn, String name) {
        ContactDTO contact = dbService.search(conn,name);
        return contact;
    }

    @Override
    public boolean delete(Connection conn, int id) {
        return dbService.delete(conn, id);

    }

    @Override
    public ContactDTO getById(Connection conn, int id) {
        ContactDTO contactDTO = dbService.getById(conn, id);

        return contactDTO;
    }

    @Override
    public boolean importExcel(Connection conn) {
        try {
            InputStream file = ContactServiceImpl.class.getClassLoader().getResourceAsStream("contact.xlsx");
            assert file != null;
            Workbook workbook = new XSSFWorkbook(file);
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue;

                    ContactDTO contactDTO = new ContactDTO();

                    if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.STRING) {
                        contactDTO.setName(row.getCell(0).getStringCellValue());
                    } else if (row.getCell(0) != null && row.getCell(0).getCellType() == CellType.NUMERIC) {
                        contactDTO.setName(String.valueOf(row.getCell(0).getNumericCellValue()));
                    }

                    if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.STRING) {
                        contactDTO.setEmail(row.getCell(1).getStringCellValue());
                    } else if (row.getCell(1) != null && row.getCell(1).getCellType() == CellType.NUMERIC) {
                        contactDTO.setEmail(String.valueOf(row.getCell(1).getNumericCellValue()));
                    }

                    if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.NUMERIC) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        contactDTO.setDob(dateFormat.format(row.getCell(2).getDateCellValue()));
                    }

                    if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.STRING) {
                        contactDTO.setMobile(row.getCell(3).getStringCellValue());
                    } else if (row.getCell(3) != null && row.getCell(3).getCellType() == CellType.NUMERIC) {
                        contactDTO.setMobile(String.valueOf(row.getCell(3).getNumericCellValue()));
                    }

                    String query = "INSERT INTO contactbook (cname, email, dob, mobile) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                        stmt.setString(1, contactDTO.getName());
                        stmt.setString(2, contactDTO.getEmail());
                        stmt.setDate(3, Date.valueOf(contactDTO.getDob()));
                        stmt.setString(4, contactDTO.getMobile());
                        stmt.executeUpdate();
                        ResultSet generatedKeys = stmt.getGeneratedKeys();
                            if (generatedKeys.next()) {

                                contactDTO.setId(generatedKeys.getInt(1));
                            }



                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;

    }

    @Override
    public boolean exportExcel(Connection conn) {
        List<ContactDTO> contacts = getAllContacts(conn);
        try {
            Workbook workbook = new XSSFWorkbook();
            OutputStream file = new FileOutputStream("export_Contact.xlsx");
            Sheet sheet = workbook.createSheet("Contacts");
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("DOB");
            header.createCell(3).setCellValue("Mobile");

            int rowNum = 1;
            for (ContactDTO contact : contacts) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(contact.getName());
                row.createCell(1).setCellValue(contact.getEmail());
                row.createCell(2).setCellValue(contact.getDob());
                row.createCell(3).setCellValue(contact.getMobile());
            }

            workbook.write(file);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
