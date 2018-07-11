package com14.company;

import java.util.Scanner; // 100/100

public class _04_Find_Evens_or_Odds {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        String typesIntegers = scan.nextLine();
        Integer start = Integer.parseInt(input[0]);
        Integer end = Integer.parseInt(input[1]);

        for (int i = start; i <= end; i++) {

            if (typesIntegers.equals("odd")) {
                if (i % 2 != 0)
                {
                    System.out.printf("%d ", i);
                }
            } else if (typesIntegers.equals("even")) {
                if (i % 2 == 0)
                {
                    System.out.printf("%d ", i);
                }
            }
        }
    }
}
