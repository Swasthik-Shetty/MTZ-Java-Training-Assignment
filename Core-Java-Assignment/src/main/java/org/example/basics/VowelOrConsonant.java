package org.example.basics;

import java.util.Scanner;

public class VowelOrConsonant {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch;
        System.out.println("Enter the character:");
        ch = sc.next().charAt(0);
        ch = Character.toLowerCase(ch);
        switch (ch) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                System.out.println("The entered character is Vowel");
                break;
            default:
                System.out.println("The entered character is Consonant");
        }
    }
}
