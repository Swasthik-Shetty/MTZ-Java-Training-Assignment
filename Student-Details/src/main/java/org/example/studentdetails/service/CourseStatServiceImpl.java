package org.example.studentdetails.service;

import org.example.studentdetails.domain.Student;

import java.util.List;
import java.util.function.Predicate;

public class StatServiceImpl implements CourseStatService {
    @Override
    public List<Student> getStudents(String Qualification) {
        return List.of();
    }

    @Override
    public int count(Predicate predicate) {
        return 0;
    }

    @Override
    public List<String> search(String str) {
        return List.of();
    }
}
