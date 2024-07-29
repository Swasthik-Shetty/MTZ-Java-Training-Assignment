package org.example.basics;

import java.util.Scanner;

public class MembershipDiscount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int amount;
        char card;
        int discount;
        int netPay;

        System.out.println("Enter the bill amount:");
        amount = sc.nextInt();

        System.out.println("Do you have a membership card?");
        card = sc.next().charAt(0);

        if (card == 'Y' || card == 'y') {
            discount = (amount * 10) / 100;
        } else {
            discount = (amount * 3) / 100;
        }

        netPay = amount - discount;

        System.out.println("Thank you! Your total bill amount is Rs " + amount + ", discount is Rs " + discount + " and net amount payable is Rs " + netPay);
    }
}
