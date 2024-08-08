package org.example.contact.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.contact.dto.ContactDTO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Iterator;
import java.util.List;

public class ContactServiceImpl implements ContactService{

    @Override
    public void create(Connection conn, ContactDTO contactDTO) {
        String teamSql = "INSERT INTO contactbook (cname,email,dob,mobile) VALUES (?, ?, ?, ?)";
        try (PreparedStatement teamStmt = conn.prepareStatement(teamSql)) {

                teamStmt.setString(1, contactDTO.getName());
                teamStmt.setString(2, contactDTO.getEmail());
            teamStmt.setDate(3, Date.valueOf(contactDTO.getDob())); // Ensure dob is in the format YYYY-MM-DD
            teamStmt.setLong(4, Long.parseLong(contactDTO.getMobile()));

            teamStmt.addBatch();
                teamStmt.executeBatch();
            System.out.println("Entered successfully");
        }catch (SQLException e)
        {
            System.out.println();
        }
    }

    @Override
    public ContactDTO update(Connection conn, PreparedStatement stmt, ContactDTO contactDTO) {
        return null;
    }

    @Override
    public List<ContactDTO> search(Connection conn,PreparedStatement stmt, ContactDTO contactDTO) {
        return List.of();
    }

    @Override
    public boolean delete(Connection conn, PreparedStatement stmt, ContactDTO contactDTO) {
        return false;
    }

    @Override
    public ContactDTO getById(Connection conn, PreparedStatement stmt, ContactDTO contactDTO) {
        return null;
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
    public boolean exportExcel(Connection conn,PreparedStatement stmt, ContactDTO contactDTO) {
        return false;
    }
}
