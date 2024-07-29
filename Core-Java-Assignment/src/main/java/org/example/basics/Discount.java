package org.example.basics;

import java.util.Scanner;

public class Discount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amount;

        System.out.println("Enter the billing amount:");
        amount = sc.nextInt();

        if (amount > 6000) {
            int discount = (amount * 10) / 100;
            amount -= discount;
        }

        System.out.println("Your net billing amount: " + amount);
    }
}
