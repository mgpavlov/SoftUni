package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   //100/100
import java.util.function.Consumer;

public class _01_Consumer_Print {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Consumer<String[]> print = str -> {
            for (String currentStr : str) {
                System.out.println(currentStr);
            }
        };

        String[] input = in.readLine().split("\\s+");
        print.accept(input);
    }
}
