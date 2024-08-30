package com.monetize360.contactapp.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.monetize360.contactapp.dao.ContactRepository;
import com.monetize360.contactapp.domain.Contact;
import com.monetize360.contactapp.dto.ContactDto;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = objectMapper.convertValue(contactDto, Contact.class);
        Contact savedContact = contactRepository.save(contact);
        log.info("Added contact with ID: {}", savedContact.getId());
        return objectMapper.convertValue(savedContact, ContactDto.class);
    }

    @Override
    public ContactDto updateContact(ContactDto contactDto) {

        Contact contact = objectMapper.convertValue(contactDto, Contact.class);

        Optional<Contact> existingContactOptional = contactRepository.findById(contact.getId());
        if (existingContactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + contact.getId());
        }


        Contact existingContact = existingContactOptional.get();
        existingContact.setFirstName(contact.getFirstName());
        existingContact.setLastName(contact.getLastName());
        existingContact.setEmail(contact.getEmail());
        existingContact.setMobile(contact.getMobile());

        Contact updatedContact = contactRepository.save(existingContact);

        log.info("Updated contact with ID: {}", updatedContact.getId());

        return objectMapper.convertValue(updatedContact, ContactDto.class);

    }

    @Override
    public ContactDto getContactById(UUID uuid) {

        return contactRepository.findById(uuid)
                .map(contact -> objectMapper.convertValue(contact, ContactDto.class))
                .orElse(null);
    }

    @Override
    public BufferedImage generateQRCode(UUID id) throws Exception {
        ContactDto contact = getContactById(id);
        if (contact == null) {
            throw new EntityNotFoundException("Contact not found with ID: " + id);
        }

        String data = "UUID:" + id +
                "\nFirst Name: " + contact.getFirstName() +
                "\nLast Name: " + contact.getLastName() +
                "\nEmail: " + contact.getEmail() +
                "\nMobile: " + contact.getMobile();

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

        log.info("Generated QR code for contact with ID: {}", id);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    @Override
    public void deleteContact(UUID id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (contactOptional.isEmpty()) {
            throw new EntityNotFoundException("Contact not found with ID: " + id);
        }

        Contact contact = contactOptional.get();
        contact.setDeleted(true);

        contactRepository.save(contact);

        log.info("Deleted contact with ID: {}", id);
    }

    @Override
    public List<ContactDto> getAllContacts(String field, String direction, int page, int size) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(field).ascending()
                : Sort.by(field).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Contact> contactPage = contactRepository.findAll(pageable);

        log.info("Contact count size is: {}", contactPage.getTotalElements());

        return objectMapper.convertValue(contactPage.getContent(), new TypeReference<List<ContactDto>>() {
        });
    }

    @Override
    public void importExcelData(MultipartFile file) {

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                Contact contact = new Contact();
                contact.setFirstName(row.getCell(0).getStringCellValue());
                contact.setLastName(row.getCell(1).getStringCellValue());
                contact.setEmail(row.getCell(2).getStringCellValue());
                contact.setMobile(String.valueOf(row.getCell(3).getNumericCellValue()));


                contactRepository.save(contact);
            }
        }catch (IOException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void importCsvData(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVReader csvReader = new CSVReader(reader)) {

            String[] line;
            boolean firstRow =true;
            while ((line = csvReader.readNext()) != null) {
                if(firstRow){
                    firstRow=false;
                    continue;
                }
                Contact contact = new Contact();
                contact.setFirstName(line[0]);
                contact.setLastName(line[1]);
                contact.setEmail(line[2]);
                contact.setMobile(line[3]);
                contactRepository.save(contact);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] exportContacts() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contacts");
            List<Contact> contacts = contactRepository.findAll();

            // Header Row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("First Name");
            headerRow.createCell(1).setCellValue("Last Name");
            headerRow.createCell(2).setCellValue("Email");
            headerRow.createCell(3).setCellValue("Mobile");

            int rowIdx = 1;
            for (Contact contact : contacts) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(contact.getFirstName());
                row.createCell(1).setCellValue(contact.getLastName());
                row.createCell(2).setCellValue(contact.getEmail());
                row.createCell(3).setCellValue(contact.getMobile());
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException e) {
            log.error("Error occurred while exporting contacts: {}", e.getMessage());
            throw new RuntimeException("Failed to export contacts");
        }
    }

    @Override
    public void sendMail(UUID id, String toEmail) {
        try {
            BufferedImage qrCode = generateQRCode(id);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrCode, "png", byteArrayOutputStream);

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Contact Details");
            helper.setText("This is the qrcode with details");
            byte[] qrCodeImage =  byteArrayOutputStream.toByteArray();;
            helper.addAttachment("Contact_Detail", new ByteArrayResource(qrCodeImage),"image/png");
            javaMailSender.send(message);

        }catch(Exception e) {
            //qrcode is empty than it causes exception
            e.printStackTrace();
        }

    }

}
