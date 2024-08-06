package org.example.empdetails.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Employee {
    private int empno;
    private double salary;
    private String job;
    private String currency;
    private String formattedSalary;
}
