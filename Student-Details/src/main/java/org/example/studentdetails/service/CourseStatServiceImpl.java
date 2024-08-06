package org.example.studentdetails.service;

import org.example.studentdetails.domain.Student;
import org.example.studentdetails.dto.CountDTO;
import org.example.studentdetails.dto.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CourseStatServiceImpl implements CourseStatService {

    List<Student> studentList = CSVFileReaderUtil.readDataFromFile();

    @Override
    public List<Student> studentsByQualification(String qualification) {

        return studentList.stream()
                .filter(student -> student.getQualification().equals(qualification))
                .collect(Collectors.toList());
    }

    @Override
    public long getStudentCountByQualification(String qualification) {
        return studentList.stream()
                .filter(student -> student.getQualification().equals(qualification))
                .count();
    }

    @Override
    public int getPlacedStudentCount() {
        return (int) studentList.stream()
                .filter(student -> student.getPlaced().equals("Y"))
                .count();
    }

    @Override
    public int getNotPlacedStudentCount() {
        return (int) studentList.stream()
                .filter(student -> student.getPlaced().equals("N"))
                .count();
    }

    @Override
    public CountDTO getPlacedAndNotPlacedCount() {
        CountDTO countDTO = new CountDTO();
        int placedCount = getPlacedStudentCount();
        int nonPlacedCount = getNotPlacedStudentCount();

        countDTO.setPlacedCount(placedCount);
        countDTO.setNotPlacedCount(nonPlacedCount);
        return countDTO;
    }

    @Override
    public List<Student> search(String str) {

        return studentList.stream()
                .filter(student -> student.getName().equalsIgnoreCase(str))
                .collect(Collectors.toList());
    }

    @Override
    public float successRate(String batchName) {


        long total = studentList.stream()
                .filter(student -> student.getBatch().equals(batchName))
                .count();

        long countSuccess = studentList.stream()
                .filter(student -> student.getBatch().equalsIgnoreCase(batchName))
                .filter(student -> "Y".equalsIgnoreCase(student.getCompleted()))
                .count();

        if (total == 0) {
            return 0.0f;
        }

        return (float) countSuccess / (float) total * 100;
    }

    @Override
    public List<Student> maxScoreStudent() {
        double maxScore = studentList.stream()
                .mapToDouble(Student::getScore)
                .max()
                .orElse(0);

        return studentList.stream()
                .filter(student -> student.getScore() == maxScore)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getStudentNames() {
        return studentList.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> allStudentsDetails() {
        return studentList.stream()
                .map(student -> {
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setName(student.getName());
                    studentDTO.setQualification(student.getQualification());
                    studentDTO.setScore(student.getScore());
                    return studentDTO;
                })
                .collect(Collectors.toList());
    }
}
