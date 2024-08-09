package org.example.contact.service;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.contact.domain.Contact;
import org.example.contact.dto.ContactDTO;
import org.modelmapper.ModelMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactServiceImpl implements ContactService {

    @Override
    public List<ContactDTO> getAllContacts(Connection conn) {
        String query = "SELECT * FROM contactbook";
        List<ContactDTO> contacts = new ArrayList<>();
        try  {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            ModelMapper modelMapper =new ModelMapper();
            while (rs.next()) {
                Contact contact = modelMapper.map(rs, Contact.class);
                contact.setName(rs.getString("cname"));
                contact.setEmail(rs.getString("email"));
                contact.setDob(rs.getDate("dob").toString());
                contact.setMobile(rs.getString("mobile"));

                contacts.add(modelMapper.map(contact, ContactDTO.class));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public boolean create(Connection conn, ContactDTO contactDTO) {
        String sql = "INSERT INTO contactbook (cname,email,dob,mobile) VALUES (?, ?, ?, ?)";
        try (PreparedStatement Stmt = conn.prepareStatement(sql)) {

            Stmt.setString(1, contactDTO.getName());
            Stmt.setString(2, contactDTO.getEmail());
            Stmt.setDate(3, Date.valueOf(contactDTO.getDob())); // Ensure dob is in the format YYYY-MM-DD
            Stmt.setLong(4, Long.parseLong(contactDTO.getMobile()));

            Stmt.addBatch();
            Stmt.executeBatch();
            Stmt.close();
            return true;

        } catch (SQLException e) {
            System.out.println();
            return false;
        }
    }

    @Override
    public boolean update(Connection conn, ContactDTO contactDTO, int updateId) {
        String sql = "UPDATE contacts SET name = ?, email = ?, dob = ?, mobile = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, contactDTO.getName());
            stmt.setString(2, contactDTO.getEmail());
            stmt.setDate(3, Date.valueOf(contactDTO.getDob())); // Ensure dob is in the format YYYY-MM-DD
            stmt.setLong(4, Long.parseLong(contactDTO.getMobile())); // Convert String to Long
            stmt.setInt(5, updateId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ContactDTO search(Connection conn, String name) {
        ContactDTO contacts = new ContactDTO();

        String sql = "SELECT * FROM contactbook WHERE cname LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + name + "%"); // Using wildcard for partial matches

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {

                contacts.setId(resultSet.getInt("id"));
                contacts.setName(name);
                contacts.setEmail(resultSet.getString("email"));
                Date dbBirthDate = resultSet.getDate("dob");
                if (dbBirthDate != null) {
                    LocalDate localBirthDate = dbBirthDate.toLocalDate();
                    contacts.setDob(localBirthDate.toString());
                }
                contacts.setMobile(resultSet.getString("mobile"));
            }
            resultSet.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return contacts;
    }

    @Override
    public boolean delete(Connection conn, int id) {
        String sql = "DELETE FROM contactbook WHERE id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("Contact with ID " + id + " not found.");
            } else {
                System.out.println("Contact with ID " + id + " deleted successfully");
            }

            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ContactDTO getById(Connection conn, int id) {
        ContactDTO contactDTO = new ContactDTO();
        String query = "SELECT * FROM contactbook where id= ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("cname");
                contactDTO.setId(id);
                contactDTO.setName(name);
                contactDTO.setEmail(resultSet.getString("email"));
                Date dbBirthDate = resultSet.getDate("dob");
                if (dbBirthDate != null) {
                    LocalDate localBirthDate = dbBirthDate.toLocalDate();
                    contactDTO.setDob(localBirthDate.toString());
                }
                contactDTO.setMobile(resultSet.getString("mobile"));
            }
            resultSet.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactDTO;
    }

    @Override
    public void importExcel(Connection conn) {
        try {
            InputStream file = ContactServiceImpl.class.getClassLoader().getResourceAsStream("contact.xlsx");
            assert file != null;
            Workbook workbook = WorkbookFactory.create(file);

            // Get first sheet from the workbook
            Sheet sheet = workbook.getSheetAt(0);

            // Assuming first row contains column names
            Row headerRow = sheet.getRow(1);


            // Create SQL INSERT statement based on column names
            StringBuilder sql = new StringBuilder("INSERT INTO contactbook (");
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sql.append(headerRow.getCell(i).getStringCellValue());
                if (i < headerRow.getLastCellNum() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") VALUES (");
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                sql.append("?");
                if (i < headerRow.getLastCellNum() - 1) {
                    sql.append(", ");
                }
            }
            sql.append(")");

            // Iterate through rows and insert data
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case STRING:
                                if (cell.getStringCellValue().matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date date = sdf.parse(cell.getStringCellValue());
                                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                    pstmt.setDate(j + 1, sqlDate);
                                } else {
                                    pstmt.setString(j + 1, cell.getStringCellValue());
                                }
                                break;
                            case NUMERIC:
                                pstmt.setInt(j + 1, (int) cell.getNumericCellValue());
                                break;

                            default:
                                pstmt.setString(j + 1, "");
                        }
                    } else {
                        pstmt.setString(j + 1, "");
                    }
                }
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            conn.close();
            workbook.close();
            file.close();


        } catch (Exception e) {
            System.out.println("Exception");
        }
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
