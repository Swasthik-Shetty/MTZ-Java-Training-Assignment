package org.example.basics;

import java.util.Scanner;

public class FactorialUsingWhileLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, temp, fact = 1;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        temp = num;
        while (temp != 0) {
            fact *= temp;
            temp--;
        }
        System.out.println("The factorial of " + num + " is: " + fact);
    }
}
