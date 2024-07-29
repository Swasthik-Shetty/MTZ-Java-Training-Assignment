package org.example.basics;

import java.util.Scanner;

public class NaturalNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        System.out.println("Enter the number of natural numbers to be generated:");
        num = sc.nextInt();
        System.out.print("First " + num + " natural numbers are :");
        for (int i = 1; i <= num; i++) {
            System.out.print(" " + i);
        }
    }
}
