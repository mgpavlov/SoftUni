package com14.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class _10_List_Of_Predicates {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        List<Predicate<Integer>> predicates = new ArrayList<>();
        int[] sequence = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        for (int number : sequence) {
            Predicate<Integer> predicate = x -> x % number == 0;
            predicates.add(predicate);
        }

        List<Integer> result = new ArrayList<>();
        for (int integer = 1; integer <= n; integer++) {
            boolean divisible = false;
            for (Predicate<Integer> predicate : predicates) {
                if (predicate.test(integer)) {
                    divisible = true;
                } else {
                    divisible = false;
                    break;
                }
            }

            if (divisible) {
                result.add(integer);
            }
        }

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
