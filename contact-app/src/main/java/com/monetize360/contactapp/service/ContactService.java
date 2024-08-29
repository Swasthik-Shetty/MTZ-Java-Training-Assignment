package com.monetize360.contactapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ContactService {
    public void importExcelData(MultipartFile file) throws IOException;
}
