package org.example.basics;

import java.util.Scanner;

import static java.lang.Math.pow;

public class SumOfSeriesWithCube {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num, sum = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();

        for (int i = 2; i <= num; i++) {
            sum += (1 / pow(i, 3.0));
        }
        System.out.println("The sum of series is: " + sum);
    }
}
