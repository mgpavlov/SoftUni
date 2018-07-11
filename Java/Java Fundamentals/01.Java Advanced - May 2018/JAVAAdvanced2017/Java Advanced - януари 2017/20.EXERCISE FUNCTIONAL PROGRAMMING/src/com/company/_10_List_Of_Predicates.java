package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;          // 100/100
import java.util.function.BiPredicate;


public class _10_List_Of_Predicates {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(in.readLine());
        String[] nums = in.readLine().split("\\s+");

        BiPredicate<Integer, String[]> isDivisible = (num, c) -> {
            for (String s : c) {
                int current = Integer.parseInt(s);
                if (num % current != 0)
                    return false;
            }

            return true;
        };

        for (int i = 1; i <= n; i++) {
            if (isDivisible.test(i, nums)) {
                System.out.print(i + " ");
            }
        }

    }
}
