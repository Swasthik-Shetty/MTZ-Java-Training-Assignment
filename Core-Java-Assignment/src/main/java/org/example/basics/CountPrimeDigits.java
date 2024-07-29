package org.example.basics;

import java.util.Scanner;

import static java.lang.Math.pow;

public class CountPrimeDigits {
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
        int num, count = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        int temp;
        while (num != 0) {
            temp = num % 10;
            if (isPrime(temp)) {
                count++;
            }

            num /= 10;
        }
        System.out.println("Number of prime digits in the entered number is: " + count);
    }
}
