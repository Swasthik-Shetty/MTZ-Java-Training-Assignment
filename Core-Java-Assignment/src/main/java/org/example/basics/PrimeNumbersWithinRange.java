package org.example.basics;

import java.util.Scanner;

public class PrimeNumbersWithinRange {
    static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int lower, upper;
        int flag, i, j;

        System.out.println("Enter the lower bound value:");
        lower = sc.nextInt();

        System.out.println("Enter the upper bound value:");
        upper = sc.nextInt();

        System.out.print("The prime numbers between " + lower + " and " + upper + " are:");

        for (i = lower; i <= upper; i++) {
            if (isPrime(i)) {
                System.out.print(" " + i);
            }
        }


    }
}
