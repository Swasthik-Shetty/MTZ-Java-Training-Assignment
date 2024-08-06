package org.example.studentdetails.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.example.studentdetails.domain.Student;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class CSVFileReaderUtil {

    private CSVFileReaderUtil() {
    }

    public static List<Student> readDataFromFile() {
        String fileName = "coursedata.csv";

        List<Student> studentList = new ArrayList<>();
        try (InputStream inputStream = CSVFileReaderUtil.class.getClassLoader().getResourceAsStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             CSVReader reader = new CSVReader(inputStreamReader)) {

            String[] nextLine;
            boolean FirstLine = true;
            while ((nextLine = reader.readNext()) != null) {
                if (FirstLine) {
                    FirstLine = false;
                    continue;
                }

                String name = nextLine[0];

                String batch = nextLine[1];

                String completed = nextLine[2];

                String placed = nextLine[3];

                String qualification = nextLine[4];

                double score = Double.parseDouble(nextLine[5]);

                Student student = new Student(name, batch, completed, placed, qualification, score);
                studentList.add(student);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return studentList;
    }

}
