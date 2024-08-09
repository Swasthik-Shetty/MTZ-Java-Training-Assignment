package org.example.contact;

import org.example.contact.dto.ContactDTO;
import org.example.contact.service.ContactServiceImpl;
import org.example.contact.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionUtil.getConnection();
        Statement stmt = ConnectionUtil.getStatement(connection);
        ContactServiceImpl contactService = new ContactServiceImpl();

        ContactDTO contactDTO = new ContactDTO();
        Scanner sc = new Scanner(System.in);

        boolean flag = true;
        contactService.importExcel(connection);


        while (flag) {
            System.out.println("Enter the choice to do operation :");
            System.out.println("1.Add new detail  2.Update detail  3.Get BY ID details 4.Search Contact  5.Delete Contact  Exit:Any other key");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the details to be added:");
                    System.out.println("Enter name:");
                    String name = sc.nextLine();

                    System.out.println("Enter email");
                    String email = sc.nextLine();

                    System.out.println("Enter dob");
                    String dob = sc.nextLine();

                    System.out.println("Enter number");
                    String mobile = sc.nextLine();

                    contactDTO.setName(name);
                    contactDTO.setEmail(email);
                    contactDTO.setDob(dob);
                    contactDTO.setMobile(mobile);

                    if (contactService.create(connection, contactDTO)) {
                        System.out.println("Entered successfully");
                    } else {
                        System.out.println("Failed to enter new details");
                    }
                    break;
                case 2:
                    System.out.print("Enter ID of contact to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();  // Consume newline

                    System.out.println("Choose field to update:");
                    System.out.println("1. Name");
                    System.out.println("2. Email");
                    System.out.println("3. Date of Birth");
                    System.out.println("4. Mobile");
                    System.out.print("Enter your choice: ");
                    int fieldChoice = sc.nextInt();
                    sc.nextLine();  // Consume newline

                    ContactDTO updateContact = contactService.getById(connection, updateId);
                    if (updateContact == null) {
                        System.out.println("Contact not found.");
                        break;
                    }

                    switch (fieldChoice) {
                        case 1:
                            System.out.print("Enter new name: ");
                            String newName = sc.nextLine();
                            updateContact.setName(newName);
                            break;
                        case 2:
                            System.out.print("Enter new email: ");
                            String newEmail = sc.nextLine();
                            updateContact.setEmail(newEmail);
                            break;
                        case 3:
                            System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                            String newDob = sc.nextLine();
                            updateContact.setDob(newDob);
                            break;
                        case 4:
                            System.out.print("Enter new mobile: ");
                            String newMobile = sc.nextLine();
                            updateContact.setMobile(newMobile);
                            break;
                        default:
                            System.out.println("Invalid choice. No changes made.");
                            continue;
                    }

                    if (contactService.update(connection, updateContact, updateId)) {
                        System.out.println("Contact updated successfully.");
                    } else {
                        System.out.println("Failed to update");
                    }
                    break;
                case 3:
                    System.out.println("Enter id to get contact details:");
                    int id = sc.nextInt();
                    contactDTO = contactService.getById(connection, id);
                    System.out.println("ID: " + contactDTO.getId() + " Name: " + contactDTO.getName() + " Email: " + contactDTO.getEmail() +
                            "DOB: " + contactDTO.getDob() + "Mobile: " + contactDTO.getMobile());
                    break;
                case 4:
                    System.out.println("Enter name to search contact details:");
                    String sname = sc.nextLine();
                    contactDTO = contactService.search(connection, sname);
                    System.out.println("ID: " + contactDTO.getId() + " Name: " + contactDTO.getName() + " Email: " + contactDTO.getEmail() +
                            "DOB: " + contactDTO.getDob() + "Mobile: " + contactDTO.getMobile());
                    break;
                case 5:
                    System.out.println("Enter id to delete contact details:");
                    int id4 = sc.nextInt();
                    if (contactService.delete(connection, id4)) {
                        System.out.println("Deleted contact details successfully ");
                    } else {
                        System.out.println("Failed to delete");
                    }
                    break;
                case 6:
                    boolean exported = contactService.exportExcel(connection);
                    if (exported) {
                        System.out.println("Contacts exported successfully.");
                    } else {
                        System.out.println("Failed to export contacts.");
                    }

                default:
                    flag = false;
                    break;
            }
        }


        ConnectionUtil.closeStatement(stmt);
        ConnectionUtil.closeConn(connection);

    }
}
