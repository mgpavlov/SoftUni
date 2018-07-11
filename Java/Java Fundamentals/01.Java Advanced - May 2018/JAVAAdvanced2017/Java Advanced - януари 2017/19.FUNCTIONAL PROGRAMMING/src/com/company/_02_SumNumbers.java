package com.company;
import java.util.*;

public class _02_SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String ivan = scanner.nextLine();
        String[] numbers = ivan.split(", ");

        Integer sum = sumNumbers(Arrays.asList(numbers));

        System.out.printf("Count = %d\n", numbers.length);
        System.out.printf("Sum = %d", sum);

    }

    private static Integer sumNumbers(List<String> myArrayList) {
        Integer sum = 0;

        for (String number : myArrayList) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}
