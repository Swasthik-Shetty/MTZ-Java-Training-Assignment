package org.example.studentdetails.service;

import org.example.studentdetails.domain.Student;
import org.example.studentdetails.dto.CountDTO;
import org.example.studentdetails.dto.StudentDTO;

import java.util.List;
import java.util.function.Predicate;

public interface CourseStatService {
    public List<Student> studentsByQualification(String qualification);

    public long getStudentCountByQualification(String qualification);

    public int getPlacedStudentCount();

    public int getNotPlacedStudentCount();

    public CountDTO getPlacedAndNotPlacedCount();

    public List<Student> search(String str);

    public float successRate(String batchName);

    public List<Student> maxScoreStudent();

    public List<String> getStudentNames();

    public List<StudentDTO> allStudentsDetails();
}
