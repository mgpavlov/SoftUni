package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   // 100/100
import java.util.Arrays;
import java.util.function.Consumer;


public class _01_Consumer_Print2
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] names = Arrays.stream(bf.readLine().split("\\s+")).toArray(String[]::new);

        Consumer<String> print = System.out::println;

        for (String name : names) {
            print.accept(name);
        }
    }
}
