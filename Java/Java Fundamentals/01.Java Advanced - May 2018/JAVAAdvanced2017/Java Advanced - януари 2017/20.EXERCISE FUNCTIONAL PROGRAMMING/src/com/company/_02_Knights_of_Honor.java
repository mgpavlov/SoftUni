package com.company;

import java.io.BufferedReader;
import java.io.IOException;                // 100/100
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class _02_Knights_of_Honor {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Consumer<String[]> print = names -> {
            for (String name : names) {
                System.out.println("Sir " + name);
            }
        };

        String[] names = in.readLine().split("\\s+");
        print.accept(names);
    }
}
