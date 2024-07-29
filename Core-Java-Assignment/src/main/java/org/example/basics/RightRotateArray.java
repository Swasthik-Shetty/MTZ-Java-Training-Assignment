package org.example.basics;

import java.util.Scanner;

public class RightRotateArray {
    static void displayArray(int[] arr, int size) {
        for (int i = 0; i < size; i++) {
            System.out.print(" " + arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size, count;

        System.out.println("Enter the size of array:");
        size = sc.nextInt();

        int[] arr = new int[size];
        System.out.println("Enter the " + size + " into the array:");
        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter the number of times the array elements need to be rotated:");
        count = sc.nextInt();

        System.out.println("Initial array:");
        displayArray(arr, size);

        System.out.println("Array after " + count + " rotations :");
        while (count != 0) {
            int temp = arr[size - 1];
            for (int i = size - 1; i > 0; i--) {
                arr[i] = arr[i - 1];
            }
            arr[0] = temp;
            displayArray(arr, size);
            count--;
        }
    }
}
