package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _05_Vowel_Count2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern pattern = Pattern.compile("[AOUIYEaoiuye]");
        Matcher matcher = pattern.matcher(reader.readLine());
        int counter = 0;
        while (matcher.find()) {
            counter++;
        }

        System.out.println("Vowels: " + counter);
    }
}
