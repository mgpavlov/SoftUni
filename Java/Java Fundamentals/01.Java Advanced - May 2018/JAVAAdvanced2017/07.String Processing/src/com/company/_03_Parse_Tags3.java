package com.company;

import java.util.Scanner;

public class _03_Parse_Tags3 {  // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (input.contains("<upcase>")) {
            int start = input.indexOf("<upcase>");
            int end = input.indexOf("</upcase>");
            String subs = input.substring(start + 8, end);
            input = input.replace("<upcase>" + subs + "</upcase>", subs.toUpperCase());
        }
        System.out.println(input);
    }
}
