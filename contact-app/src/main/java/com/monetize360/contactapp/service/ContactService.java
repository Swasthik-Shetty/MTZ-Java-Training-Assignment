package com.monetize360.contactapp.service;

import com.monetize360.contactapp.dto.ContactDto;

import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ContactService {
    ContactDto addContact(ContactDto contactDto);
    ContactDto updateContact(ContactDto contactDto);
    ContactDto getContactById(UUID uuid);
    BufferedImage generateQRCode(UUID id) throws Exception;
    void deleteContact(UUID uuid);
    List<ContactDto> getAllContacts(String field, String direction, int page, int size);
    void importExcelData(MultipartFile file);
    void importCsvData(MultipartFile file);
    byte[] exportContacts();

   void sendMail(UUID id, String toEmail);
}
