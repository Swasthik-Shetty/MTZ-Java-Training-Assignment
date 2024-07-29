package org.example.basics;

import java.util.Scanner;



public class NumberOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, count = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        while (num != 0) {
            count++;
            num /= 10;
        }
        System.out.println("The number of digits in the entered number is: " + count);
    }
}
