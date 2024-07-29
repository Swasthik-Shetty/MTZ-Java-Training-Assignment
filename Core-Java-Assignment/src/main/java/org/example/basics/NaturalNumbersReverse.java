package org.example.basics;

import java.util.Scanner;

public class NaturalNumbersReverse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        System.out.println("Enter the number of natural numbers to be generated:");
        num = sc.nextInt();
        System.out.print("First " + num + " natural numbers in descending order are :");
        for (int i = num; i > 0; i--) {
            System.out.print(" " + i);
        }
    }
}
