package org.example.basics;

import java.util.Scanner;

public class Add2Numbers {
    public static void main(String[] args) {
        int num1, num2;
        int sum = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the num1 value: ");
        num1 = sc.nextInt();

        System.out.println("Enter the num2 value: ");
        num2 = sc.nextInt();

        sum = num1 + num2;

        System.out.println("Sum of " + num1 + " and " + num2 + " is " + sum);
    }
}
