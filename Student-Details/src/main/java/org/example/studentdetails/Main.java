package org.example.studentdetails;

import org.example.studentdetails.domain.Student;
import org.example.studentdetails.dto.CountDTO;
import org.example.studentdetails.dto.StudentDTO;
import org.example.studentdetails.service.CourseStatServiceImpl;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseStatServiceImpl courseStatService = new CourseStatServiceImpl();

        //Question 1
        System.out.println("Enter qualification of students to be displayed: ");
        String qualification = sc.nextLine();
        List<Student> studentDTOS = courseStatService.studentsByQualification(qualification);
        studentDTOS.forEach(System.out::println);
        System.out.println();


        //Question 2
        System.out.println("Enter qualification of students to be display count: ");
        String qualificationCount = sc.nextLine();
        System.out.println("Count by Qualification: " + courseStatService.getStudentCountByQualification(qualificationCount));
        System.out.println();

        //Question 3 and 4
        System.out.println("Count of placed students: " + courseStatService.getPlacedStudentCount());
        System.out.println("Count of non-placed students: " + courseStatService.getNotPlacedStudentCount());
        System.out.println();


        //Question 5
        CountDTO countDTO = courseStatService.getPlacedAndNotPlacedCount();
        System.out.println("Count of students placed and non-placed:");
        System.out.println("Placed students: " + countDTO.getPlacedCount());
        System.out.println("Non-placed students: " + countDTO.getNotPlacedCount());
        System.out.println();

        //Question 6
        System.out.println("Enter the name of student to be searched:");
        String name = sc.nextLine();
        List<Student> student = courseStatService.search(name);
        if (!student.isEmpty()) {
            student.forEach(System.out::println);
        } else {
            System.out.println("Not found student with name " + name);
        }
        System.out.println();

        //Question 7
        System.out.println("Enter the batch name to check its success rate : ");
        String batch = sc.nextLine();
        System.out.println("Success rate of students of batch " + batch + " is : " + courseStatService.successRate(batch) + "%");
        System.out.println();

        //Question 8
        List<Student> studentList = courseStatService.maxScoreStudent();
        System.out.println("Max scored student details :");
        studentList.forEach(System.out::println);
        System.out.println();

        //Question 9
        System.out.println("List of student names : ");
        List<String> names = courseStatService.getStudentNames();
        names.forEach(System.out::println);
        System.out.println();

        //Question 10
        System.out.println("All students details with their qualification and scores :");
        List<StudentDTO> studentDTOList = courseStatService.allStudentsDetails();
        studentDTOList.forEach(System.out::println);

    }
}
