package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class _02_SumNumbers2 {

    public static void main(String[] args) throws IOException {

        Function<String, Integer> getInputLength = input -> input.split(", ").length;
        Function<String, Integer> getInputSum = input -> {
            String[] numsAsString = input.split(", ");
            int sum = 0;
            for (String num : numsAsString) {
                sum += Integer.parseInt(num.trim());
            }

            return sum;
        };

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        int length = getInputLength.apply(input);
        int sum = getInputSum.apply(input);

        System.out.println("Count = " + length);
        System.out.println("Sum = " + sum);
    }
}
