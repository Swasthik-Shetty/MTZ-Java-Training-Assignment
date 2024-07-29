package org.example.basics;

public class NumberMatrixPatternQ26 {
    public static void main(String[] args) {
        int num = 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
        }
    }
}
