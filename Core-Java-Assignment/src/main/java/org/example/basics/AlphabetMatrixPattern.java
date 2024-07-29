package org.example.basics;

public class AlphabetMatrixPattern {
    public static void main(String[] args) {
        char alpha = 'A';
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(alpha + " ");
                alpha += 1;
            }
            System.out.println();
        }
    }
}
