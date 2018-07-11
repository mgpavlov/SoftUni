package com14.company;

import java.util.Scanner; // 100/100

public class _01_Consumer_Print {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("\\s+");
        for (String each : input) {
            System.out.printf("%s\n", each);
        }

    }
}
