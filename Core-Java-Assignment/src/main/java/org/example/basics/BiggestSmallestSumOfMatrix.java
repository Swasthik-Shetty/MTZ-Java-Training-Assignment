package org.example.basics;

import java.util.Scanner;

public class BiggestSmallestSumOfMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 3x3 matrix values:");
        int[][] mat = new int[3][3];
        int biggest = Integer.MIN_VALUE, smallest = Integer.MAX_VALUE, sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = sc.nextInt();
                biggest = Math.max(biggest, mat[i][j]);
                smallest = Math.min(smallest, mat[i][j]);
                sum += mat[i][j];
            }
        }
        System.out.println("The entered matrix is : ");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Biggest element in matrix is : " + biggest);
        System.out.println("Smallest element in matrix is : " + smallest);
        System.out.println("Sum of elements present in matrix is : " + sum);
    }
}
