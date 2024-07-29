package org.example.basics;

import java.util.Scanner;

public class SimpleInterest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        float amount, rate, time, SimpleInterest;

        System.out.println("Enter the principal amount:");
        amount = sc.nextFloat();

        System.out.println("Enter the rate of interest:");
        rate = sc.nextFloat();

        System.out.println("Enter the time (years):");
        time = sc.nextFloat();

        SimpleInterest = (amount * rate * time) / 100;

        System.out.println("Simple interest is " + SimpleInterest);
    }
}
