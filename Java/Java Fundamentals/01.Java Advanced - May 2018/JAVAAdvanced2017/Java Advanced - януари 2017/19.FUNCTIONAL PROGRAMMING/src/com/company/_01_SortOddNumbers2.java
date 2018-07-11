package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _01_SortOddNumbers2 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] numbersAsStr = in.readLine().split(",\\s+");
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String NumbersAsStr : numbersAsStr) {
            numbers.add(Integer.parseInt(NumbersAsStr.trim()));
        }

        numbers.removeIf(n -> n % 2 != 0);
        String toPrint1 = String.format("%s", numbers).replaceFirst("\\[", "").replaceFirst("\\]", "");
        System.out.println(toPrint1);

        numbers.sort((n1, n2) -> n1.compareTo(n2));
        String toPrint2 = String.format("%s", numbers).replaceFirst("\\[", "").replaceFirst("\\]", "");
        System.out.println(toPrint2);
    }

}
