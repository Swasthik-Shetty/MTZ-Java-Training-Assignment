package org.example.basics;

import java.util.Scanner;

public class NumberDenomination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num, denomination = 1000;
        int digit;
        System.out.println("Enter any number:");
        num = sc.nextInt();

        while (denomination != 0) {
            digit = (num / denomination);

            System.out.println(digit + " * " + denomination + " = " + (digit * denomination));
            num -= (digit * denomination);
            denomination /= 10;

        }

    }
}
