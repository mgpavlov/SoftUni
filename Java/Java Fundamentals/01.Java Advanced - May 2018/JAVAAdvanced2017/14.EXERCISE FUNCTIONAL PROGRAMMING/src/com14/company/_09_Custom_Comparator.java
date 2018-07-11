package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class _09_Custom_Comparator {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Integer[] sequence = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toArray(Integer[]::new);

        Comparator<Integer> evenBeforeOdd = (a, b) -> {
            if (a % 2 != 0 && b % 2 == 0) {
                return 1;
            } else if (a % 2 == 0 && b % 2 != 0) {
                return -1;
            } else {
                return a.compareTo(b);
            }
        };

        Arrays.sort(sequence, evenBeforeOdd);

        for (Integer integer : sequence) {
            System.out.print(integer + " ");
        }
    }
}
