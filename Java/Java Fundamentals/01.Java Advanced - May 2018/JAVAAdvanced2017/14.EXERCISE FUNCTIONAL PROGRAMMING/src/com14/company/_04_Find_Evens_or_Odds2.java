package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class _04_Find_Evens_or_Odds2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split(" ");

        int lowerBound = Integer.parseInt(input[0]);
        int upperBound = Integer.parseInt(input[1]);

        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }

        String condition = reader.readLine();

        Predicate<Integer> filter = evenOrOdd(condition);
        Consumer<Integer> printer = integer -> System.out.print(integer + " ");

        getRangeOnCondition(lowerBound, upperBound, filter, printer);
    }

    private static void getRangeOnCondition(int lowerBound, int upperBound, Predicate<Integer> predicate, Consumer<Integer> printer) {
        for (int i = lowerBound; i <= upperBound; i++) {
            if (predicate.test(i)) {
                printer.accept(i);
            }
        }
    }

    private static Predicate<Integer> evenOrOdd(String condition) {
        switch (condition) {
            case "odd":
                return x -> x % 2 != 0;
            case "even":
                return x -> x % 2 == 0;
        }
        return x -> x != 0;
    }
}
