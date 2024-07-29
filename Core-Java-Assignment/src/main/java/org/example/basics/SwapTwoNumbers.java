package org.example.basics;

import java.util.*;

public class SwapTwoNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1, num2;
        int temp = 0;
        System.out.println("Enter the first number num1:");
        num1 = sc.nextInt();

        System.out.println("Enter the first number num2:");
        num2 = sc.nextInt();

        System.out.println("Before swap, the values of num1 = " + num1 + " and num2 = " + num2);

        temp = num1;
        num1 = num2;
        num2 = temp;

        System.out.println("After swap, the values of num1 = " + num1 + " and num2 = " + num2);
    }
}
