package org.example.basics;

import java.util.Scanner;

public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int prev = 0, current = 1;
        int temp;
        System.out.println("Enter the upper bound number to generate the Fibonacci numbers:");
        num = sc.nextInt();
        if (num == 0) {
            System.out.println("0");
        } else if (num == 1) {
            System.out.println("Fibonacci series up to the entered number is: 0 1");
        } else {
            System.out.print("Fibonacci series up to the entered number is: 0 1");
            for (int i = current; i < num; ) {
                temp = current;
                current += prev;
                prev = temp;
                System.out.print(" " + current);
                i = current;
            }
        }


    }
}
