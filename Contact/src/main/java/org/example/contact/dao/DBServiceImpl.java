package org.example.contact.dao;

import org.example.contact.domain.Contact;
import org.example.contact.dto.ContactDTO;
import org.modelmapper.ModelMapper;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBServiceImpl implements DBService{
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
        String sql = "UPDATE contactbook SET cname = ?, email = ?, dob = ?, mobile = ? WHERE id = ?";
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
}
