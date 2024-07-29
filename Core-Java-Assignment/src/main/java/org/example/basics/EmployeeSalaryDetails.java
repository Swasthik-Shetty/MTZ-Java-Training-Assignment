package org.example.basics;

import java.util.*;

public class EmployeeSalaryDetails {
    public static void main(String[] args) {
        int empno;
        String name;
        int monthlySalary;
        int yearlySalary;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the empno:");
        empno = sc.nextInt();

        System.out.println("Enter the employee name:");
        name = sc.next();

        System.out.println("Enter the monthly salary:");
        monthlySalary = sc.nextInt();

        yearlySalary = monthlySalary * 12;

        System.out.println("Hi " + name + "! Your employee id is " + empno + ", monthly salary is Rs " + monthlySalary + " and yearly salary is Rs " + yearlySalary);
    }
}
