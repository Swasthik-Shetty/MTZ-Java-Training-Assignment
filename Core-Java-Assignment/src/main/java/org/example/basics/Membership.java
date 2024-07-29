package org.example.basics;

import java.util.Scanner;

public class Membership {
    static void displayMessage(String name, int age) {
        if (age < 18) {
            System.out.println("Sorry! You need to be at least 18 years old to get membership.");
            return;
        }

        System.out.println("Congratulations " + name + " for your successful registration.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int age, mobileNum;
        String name = "";

        System.out.println("Enter the name:");
        name = sc.nextLine();

        System.out.println("Enter the  mobile number:");
        mobileNum = sc.nextInt();

        System.out.println("Enter the age:");
        age = sc.nextInt();

        displayMessage(name, age);

    }
}
