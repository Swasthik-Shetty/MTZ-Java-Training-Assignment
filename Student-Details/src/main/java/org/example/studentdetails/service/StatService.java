package org.example.studentdetails.service;

import org.example.studentdetails.domain.Student;

import java.util.List;
import java.util.function.Predicate;

public interface StatService {
    List<Student> getStudents(String Qualification);
    int count(Predicate predicate);
    List<String> search(String str);
}
