package org.example.basics;

import java.util.Scanner;

public class RightTriangleChangingNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int count = 1;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(count + " ");
                count++;
            }
            System.out.println();
        }
    }
}
