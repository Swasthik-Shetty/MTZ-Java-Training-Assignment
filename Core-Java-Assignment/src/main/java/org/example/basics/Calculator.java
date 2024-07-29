package org.example.basics;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num1, num2;
        int option;
        System.out.println("Enter the 1st Operand num1:");
        num1 = sc.nextInt();
        System.out.println("Enter the 2nd Operand num2:");
        num2 = sc.nextInt();
        System.out.println("Enter the operator");
        System.out.println("1. add 2. sub 3. mul 4. mod 5. div");
        option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("The sum of " + num1 + " and " + num2 + " is " + (num1 + num2));
                break;
            case 2:
                System.out.println("The difference of " + num1 + " and " + num2 + " is " + (num1 - num2));
                break;
            case 3:
                System.out.println("The product of " + num1 + " and " + num2 + " is " + (num1 * num2));
                break;
            case 4:
                if (num2 != 0) {
                    System.out.println("The remainder of " + num1 + " divided by " + num2 + " is " + (num1 % num2));
                } else {
                    System.out.println("Divide by zero error");
                }
                break;
            case 5:
                if (num2 != 0) {
                    System.out.println("The quotient of " + num1 + " divided by " + num2 + " is " + (num1 / num2));
                } else {
                    System.out.println("Divide by zero error");
                }
                break;
            default:
                System.out.println("Invalid Option!!");
        }
    }
}
