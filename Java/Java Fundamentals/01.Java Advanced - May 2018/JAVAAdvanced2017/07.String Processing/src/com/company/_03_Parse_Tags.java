package com.company;

import java.util.Scanner;  // 100/100

public class _03_Parse_Tags {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        StringBuilder print = new StringBuilder(input);
        for (int i = 0; i < print.length() - 1; i++) {
            if (print.charAt(i) == print.charAt(i + 1)) {
                print.deleteCharAt(i);
                i--;
            }
        }
        System.out.println(print);
    }
}
