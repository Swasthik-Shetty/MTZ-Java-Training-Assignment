package org.example.basics;

import java.util.Scanner;

public class IncrementEachDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        System.out.println("Enter any number:");
        num = sc.nextInt();
        int add = 1;
        while (add <= 1000) {
            num = num + add;
            add = add * 10;

        }
        System.out.println("Number after increment of digits: " + num);
    }
}
