package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class _04_AddVAT {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        UnaryOperator<Double> addVAT = (x) -> x * 1.2;
        String[] input = reader.readLine().split(", ");
        List<Double> numbers = new ArrayList<>();
        for (String s : input) {
            numbers.add(Double.parseDouble(s));
        }

        System.out.println("Prices with VAT:");

        for (Double number : numbers) {
            System.out.println((String.format("%1$.2f", addVAT.apply(number))).replace(".", ","));
        }
    }
}
