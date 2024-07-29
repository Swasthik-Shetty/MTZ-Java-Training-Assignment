package org.example.basics;

import java.util.Scanner;

public class NegativeToPositiveNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        System.out.println("Enter a number:");
        num = sc.nextInt();

        if (num < 0) {
            num *= -1;
        }

        System.out.println("The result is: " + num);
    }
}
