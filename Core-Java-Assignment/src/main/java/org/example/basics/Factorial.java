package org.example.basics;

import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, fact = 1;
        System.out.println("Enter any number:");
        num = sc.nextInt();

        for (int i = num; i > 0; i--) {
            fact *= i;
        }
        System.out.println("The factorial of " + num + " is: " + fact);
    }
}
