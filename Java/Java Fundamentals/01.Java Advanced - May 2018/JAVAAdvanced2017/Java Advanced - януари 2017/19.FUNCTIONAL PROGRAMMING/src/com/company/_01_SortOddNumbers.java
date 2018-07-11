package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class _01_SortOddNumbers {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String ivan = scanner.nextLine();
        String[] numbers = ivan.split(", ");
        List<Integer> myArrayList = new ArrayList<>();


        for (String number : numbers) {
            Integer cuurentNumber = Integer.parseInt(number);

            if (cuurentNumber % 2 == 0) {
                myArrayList.add(cuurentNumber);
            }
        }
        if (!myArrayList.isEmpty()) {
            String toPrint1 = String.format("%s", myArrayList).replaceFirst("\\[", "").replaceFirst("\\]", "");
            System.out.println(toPrint1);

            Collections.sort(myArrayList);

            String toPrint2 = String.format("%s", myArrayList).replaceFirst("\\[", "").replaceFirst("\\]", "");
            System.out.println(toPrint2);
        }
    }
}
