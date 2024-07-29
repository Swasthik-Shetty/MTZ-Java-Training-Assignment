package org.example.basics;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, reverseNum = 0;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        System.out.print("Reverse of the entered number is: ");
        while (num != 0) {
            reverseNum = (reverseNum * 10) + (num % 10);
            if (num % 10 == 0) {
                System.out.print(0);
            }
            num /= 10;
        }
        System.out.println(reverseNum);
    }
}
