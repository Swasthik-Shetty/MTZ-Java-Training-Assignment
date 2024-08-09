package org.example.contact.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.contact.dto.ContactDTO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

public class ContactServiceImpl implements ContactService{

    @Override
    public void create(Connection conn, ContactDTO contactDTO) {
        String sql = "INSERT INTO contactbook (cname,email,dob,mobile) VALUES (?, ?, ?, ?)";
        try (PreparedStatement Stmt = conn.prepareStatement(sql)) {

                Stmt.setString(1, contactDTO.getName());
                Stmt.setString(2, contactDTO.getEmail());
            Stmt.setDate(3, Date.valueOf(contactDTO.getDob()))
            Stmt.setLong(4, Long.parseLong(contactDTO.getMobile()));

            Stmt.addBatch();
            Stmt.executeBatch();
            Stmt.close();
            System.out.println("Entered successfully");
        }catch (SQLException e)
        {
            System.out.println();
        }
    }

    @Override
    public void update(Connection conn, ContactDTO contactDTO) {
        String sql = "UPDATE contacts SET cname = ?, email = ?, dob = ?, mobile = ? WHERE id = ?";
        try (PreparedStatement Stmt = conn.prepareStatement(sql)) {

            Stmt.setString(1, contactDTO.getName());
            Stmt.setString(2, contactDTO.getEmail());
            Stmt.setDate(3, Date.valueOf(contactDTO.getDob())); // Ensure dob is in the format YYYY-MM-DD
            Stmt.setLong(4, Long.parseLong(contactDTO.getMobile()));
            Stmt.setInt(5,contactDTO.getId());

            Stmt.addBatch();
            Stmt.executeBatch();
            Stmt.close();
            System.out.println("Updated successfully");
        }catch (SQLException e)
        {
            System.out.println();
        }
    }

    @Override
    public List<ContactDTO> search(Connection conn, ContactDTO contactDTO) {
        return List.of();
    }

    @Override
    public boolean delete(Connection conn,  ContactDTO contactDTO) {
        return false;
    }

    @Override
    public ContactDTO getById(Connection conn, int id) {
        ContactDTO contactDTO = new ContactDTO();
        String query = "SELECT * FROM contactbook where id= ?";
        try{
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
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

        }catch (SQLException e){
            e.printStackTrace();
        }
        return contactDTO;
    }

    @Override
    public void importExcel(Connection conn) {
        try {
            InputStream file = ContactServiceImpl.class.getClassLoader().getResourceAsStream("contact.xlsx");
            assert file !=null;
            Workbook wb = new XSSFWorkbook(file);
            Sheet sheet = wb.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO contactBook VALUES (?, ?, ?, ?, ?)");



            rowIterator.next(); // skip the header row

            while (rowIterator.hasNext()) {
                Row nextRow = rowIterator.next();
                Iterator<Cell> cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell nextCell = cellIterator.next();

                    int columnIndex = nextCell.getColumnIndex();

                    switch (columnIndex) {
                        case 0:

                            int id =(int)nextCell.getNumericCellValue();
                            stmt.setInt(1, id);
                            break;
                        case 1://name
                            String cname = nextCell.getStringCellValue();
                            stmt.setString(2, cname);
                        case 2://email
                            String email = nextCell.getStringCellValue();
                            stmt.setString(3, email);
                        case 3://dob
                            String dob = nextCell.getStringCellValue();
                            stmt.setString(4, dob);
                        case 4://mob
                            String mobile = nextCell.getStringCellValue();
                            stmt.setString(5, mobile);
                            break;
                    }

                }

                stmt.addBatch();


            }

            wb.close();

            // execute the remaining queries
            stmt.executeBatch();


        }catch(IOException | SQLException e)
        {
            System.out.println("Exception");
        }
    }

    @Override
    public void exportExcel(Connection conn, ContactDTO contactDTO) {

    }
}
