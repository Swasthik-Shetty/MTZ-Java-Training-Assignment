package org.example.basics;

import java.util.Scanner;

public class PatternGenerationUsingContinue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        for (int i = 1; i <= num; i++) {
            for (int j = 1; j <= num; j++) {
                if (i == j) {
                    continue;
                }
                System.out.println(i + " " + j);
            }
        }

//        Sample Output
//        1 2
//        1 3
//        2 1
//        2 3
//        3 1
//        3 2
    }
}
