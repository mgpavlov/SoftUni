package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;           // 100/100
import java.util.function.Predicate;

public class _07_Predicate_For_Names {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(in.readLine());
        String[] names = in.readLine().split("\\s+");

        Predicate<String> checker = name -> name.length() <= n;

        for (String name : names) {
            if (checker.test(name)) {
                System.out.println(name);
            }
        }

    }
}
