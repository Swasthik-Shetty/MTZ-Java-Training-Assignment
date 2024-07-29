package org.example.basics;

import java.util.Scanner;

public class TotalMarksGrade {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks1, marks2, marks3;
        int total;
        float avg;
        System.out.println("Enter the marks scored in 1st subject:");
        marks1 = sc.nextInt();

        System.out.println("Enter the marks scored in 2nd subject:");
        marks2 = sc.nextInt();

        System.out.println("Enter the marks scored in 3rd subject:");
        marks3 = sc.nextInt();

        total = marks1 + marks2 + marks3;
        avg = (float) total / 3;

        System.out.println("Total marks: " + total);
        System.out.println("Average is: " + avg);
        if (avg <= 35.0) {
            System.out.println("Grade: “C”");
        } else if (avg <= 60.0) {
            System.out.println("Grade: “B”");
        } else {
            System.out.println("Grade: “A”");
        }
    }
}
