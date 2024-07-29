package org.example.basics;

import java.util.Scanner;


public class SingleDigitSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, sum = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();

        while (num > 0 || sum > 9) {
            if (num == 0) {
                num = sum;
                sum = 0;
            }
            sum += (num % 10);
            num /= 10;

        }
        System.out.println("Single digit sum is:" + sum);

    }
}
