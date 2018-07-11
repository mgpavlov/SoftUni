package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.function.Function;

public class _08_Find_The_Smallest_Element2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Integer[] sequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);

        Function<Integer[], Integer> findMinimum = integers -> {
            Integer minIndex = -1;
            Integer minimum = Integer.MAX_VALUE;
            for (int i = 0; i < integers.length; i++) {
                if (integers[i] <= minimum) {
                    minimum = integers[i];
                    minIndex = i;
                }
            }
            return minIndex;
        };
        System.out.println(findMinimum.apply(sequence));
    }
}
