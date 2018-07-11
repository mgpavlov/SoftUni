package com.company;

import java.util.Scanner;
import java.util.regex.Matcher; // 100/100
import java.util.regex.Pattern;

public class _07_Valid_Usernames3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String pattern = "^[a-zA-Z0-9-_]{3,16}$";

        StringBuilder result = new StringBuilder();
        boolean allowPrint = false;
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("END")) {
                break;
            }

            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);

            if (matcher.find())
            {
                result.append("valid\n");
                allowPrint = true;
            }
            else if (!"".equals(line)) {
                result.append("invalid\n");
            }
        }

        if (allowPrint) {
            System.out.println(result.toString());
        }
    }
}
