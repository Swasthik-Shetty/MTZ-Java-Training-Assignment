package org.example.basics;

import java.util.Scanner;

public class SumOfSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float num, sum = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();

        for (int i = 1; i <= num; i++) {
            sum += ((float) 1 / i);
        }
        System.out.println("The sum of series is: " + sum);
    }
}
