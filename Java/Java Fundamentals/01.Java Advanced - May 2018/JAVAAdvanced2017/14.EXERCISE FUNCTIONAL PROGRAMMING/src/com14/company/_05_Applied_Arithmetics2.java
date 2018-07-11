package com14.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   // 100/100
import java.util.Arrays;
import java.util.function.Function;

public class _05_Applied_Arithmetics2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = Arrays.stream(bf.readLine().split("\\s+")).mapToInt(Integer::valueOf).toArray();

        Function<Integer, Integer> addOne = num -> num + 1;
        Function<Integer, Integer> subtractOne = num -> num - 1;
        Function<Integer, Integer> multiplyByTwo = num -> num * 2;

        while (true) {
            String command = bf.readLine();
            if ("end".equalsIgnoreCase(command)) {
                break;
            }

            if ("add".equalsIgnoreCase(command)) {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = addOne.apply(nums[i]);
                }
            } else if ("subtract".equalsIgnoreCase(command)) {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = subtractOne.apply(nums[i]);
                }
            } else if ("multiply".equalsIgnoreCase(command)) {
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = multiplyByTwo.apply(nums[i]);
                }
            } else if ("print".equalsIgnoreCase(command)) {
                System.out.println(Arrays.toString(nums).replaceAll("[\\[\\],]", ""));
            }
        }
    }
}
