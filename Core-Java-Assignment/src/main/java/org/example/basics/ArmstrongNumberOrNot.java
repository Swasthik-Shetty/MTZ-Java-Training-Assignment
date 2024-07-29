package org.example.basics;

import java.util.Scanner;

import static java.lang.Math.pow;

public class ArmstrongNumberOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double num, temp, sum = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        temp = num;
        while (temp != 0) {
            sum += pow((temp % 10), 3);
            temp /= 10;
        }
        if (num == sum) {
            System.out.print(num + " is an Armstrong number");
        } else {
            System.out.print(num + " is not an Armstrong number");
        }
    }
}
