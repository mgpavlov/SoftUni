package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.function.Consumer;
import java.util.function.Predicate;

public class _07_Predicate_For_Names2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(reader.readLine());
        Predicate<String> checkLength = string -> string.length() <= number;

        String[] input = reader.readLine().split(" ");

        Consumer<String> print = System.out::println;

        for (String word : input) {
            if (checkLength.test(word)) {
                print.accept(word);
            }
        }
    }
}
