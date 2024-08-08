package org.example.contact;

import org.example.contact.dto.ContactDTO;
import org.example.contact.service.ContactServiceImpl;
import org.example.contact.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionUtil.getConnection();
        Statement stmt = ConnectionUtil.getStatement(connection);
        ContactServiceImpl contactService = new ContactServiceImpl();

        ContactDTO contactDTO = new ContactDTO();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name:");
        String name = sc.nextLine();
        contactDTO.setName(name);
        System.out.println("Enter email");
        contactDTO.setEmail(sc.nextLine());
        System.out.println("Enter dob");
        contactDTO.setDob(sc.nextLine());
        System.out.println("Enter number");
        contactDTO.setMobile(sc.nextLine());
       contactService.create(connection,contactDTO);
        ConnectionUtil.closeStatement(stmt);
        ConnectionUtil.closeConn(connection);

    }
}
