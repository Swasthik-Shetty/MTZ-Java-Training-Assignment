package org.example.basics;

import java.util.Scanner;

public class RightTriangleInvertedIncreasingPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int count = 1;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        for (int i = num; i >= 1; i--) {
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
