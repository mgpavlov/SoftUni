package com.company;

import java.util.Scanner;

public class _07_Valid_Usernames {            // 50/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (true) {
            String curent = scan.nextLine();

            if (curent.equals("END")) {
                break;
            }
            if (curent.length() < 3 || curent.length() > 16) {
                System.out.printf("invalid\n");
            } else {
                System.out.printf("valid\n");
            }


        }
    }
}
