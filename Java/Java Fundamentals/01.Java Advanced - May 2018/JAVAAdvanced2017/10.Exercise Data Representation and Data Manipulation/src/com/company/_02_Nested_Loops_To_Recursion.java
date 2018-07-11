package com.company;

import java.io.BufferedReader;   // 100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _02_Nested_Loops_To_Recursion {
    private static int numberOfLoops;
    private static int[] loops;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        numberOfLoops = Integer.parseInt(reader.readLine());
        loops = new int[numberOfLoops];
        simulateLoops(0);
    }

    private static void simulateLoops(int current) {
        if (current == numberOfLoops) {
            printLoop();
            return;
        }
        for (int i = 1; i <= numberOfLoops; i++) {
            loops[current] = i;
            simulateLoops(current + 1);
        }
    }

    private static void printLoop() {
        System.out.println(Arrays.toString(loops).replaceAll("[\\]\\[,]", ""));
    }
}
