package com.monetize360.contactapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.contactapp.dao.ContactRepository;
import com.monetize360.contactapp.domain.Contact;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService{

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    ObjectMapper objectMapper;

    public void importExcelData(MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                Contact contact = new Contact();
                contact.setFirstName(row.getCell(0).getStringCellValue());
                contact.setLastName(row.getCell(1).getStringCellValue());
                contact.setEmail(row.getCell(2).getStringCellValue());
                contact.setMobile(row.getCell(0).getStringCellValue());

                contactRepository.save(contact);
            }
        }
    }
}
