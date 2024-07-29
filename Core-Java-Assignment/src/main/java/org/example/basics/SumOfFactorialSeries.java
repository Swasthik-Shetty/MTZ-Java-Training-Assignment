package org.example.basics;

import java.util.Scanner;

public class SumOfFactorialSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num, fact = 1;
        double sum = 0;
        System.out.println("Enter any number:");
        num = sc.nextDouble();

        for (int i = 1; i <= num; i++) {
            fact *= i;
            sum += (i / fact);
        }
        System.out.println("The sum of the series is : " + sum);
    }
}
