package org.example.basics;

import java.util.Scanner;

public class SumOfOddSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, sum = 1;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        System.out.print("The series is: 1");
        for (int i = 3; i <= num; i++) {
            if (i % 2 != 0) {
                System.out.print("," + i);
                sum += i;
            }

        }

        System.out.println("\nThe sum of odd series is: " + sum);
    }
}
