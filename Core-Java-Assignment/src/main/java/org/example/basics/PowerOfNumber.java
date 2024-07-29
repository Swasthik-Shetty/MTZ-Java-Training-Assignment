package org.example.basics;

import java.util.Scanner;

public class PowerOfNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, power;
        int finalVal;
        System.out.println("Enter the number:");
        num = sc.nextInt();
        System.out.println("Enter the power:");
        power = sc.nextInt();
        int temp = power - 1;
        finalVal = num;
        while (temp != 0) {
            finalVal *= num;
            temp--;
        }
        System.out.println("The number after calculating " + num + "^" + power + " is : " + finalVal);
    }
}
