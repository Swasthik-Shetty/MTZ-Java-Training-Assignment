package org.example.basics;

public class Pattern37 {
    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.print("* ");
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print("            *");
            System.out.print("           *");
            System.out.println();
        }

        for (int i = 0; i < 6; i++) {
            System.out.print("* * ");
        }
        System.out.println("*");

        for (int i = 0; i < 3; i++) {
            System.out.print("*           ");
            System.out.print("*            ");
            System.out.println();
        }

        for (int i = 0; i < 6; i++) {
            System.out.print("  ");
        }

        for (int i = 0; i < 7; i++) {
            System.out.print("* ");
        }

    }
}
