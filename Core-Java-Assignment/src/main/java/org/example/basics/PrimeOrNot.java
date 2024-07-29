package org.example.basics;

import java.util.Scanner;

public class PrimeOrNot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int count = 0;

        System.out.println("Enter any number:");
        num = sc.nextInt();

        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                count++;
                if (count > 1) {
                    break;
                }
            }
        }

        if (count != 1) {
            System.out.println("The entered number " + num + " is not a prime number");
        } else {
            System.out.println("The entered number " + num + " is a prime number");
        }
    }
}
