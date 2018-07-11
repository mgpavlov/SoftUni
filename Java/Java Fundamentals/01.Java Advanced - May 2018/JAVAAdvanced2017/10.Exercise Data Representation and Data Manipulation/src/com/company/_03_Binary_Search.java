package com.company;

import java.util.Scanner;  // 100/100

public class _03_Binary_Search {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        Integer n = Integer.parseInt(scan.nextLine());

        boolean found = true;

        for (int i = 0; i < input.length; i++) {
            if (Integer.parseInt(input[i]) == n) {
                System.out.println(i);
                found = false;
                break;
            }
        }
        if (found) {
            System.out.println(-1);
        }
    }
}
