package com.company;

import java.util.Scanner;  // 100/100

public class _01_Reverse_Array {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");

        for (int i = input.length-1; i >= 0; i--) {
            System.out.printf("%s ",input[i]);
        }
    }
}
