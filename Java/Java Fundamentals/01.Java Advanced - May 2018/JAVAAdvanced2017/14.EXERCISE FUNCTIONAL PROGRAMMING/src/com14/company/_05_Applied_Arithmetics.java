package com14.company;

import java.util.ArrayList;   // 100/100
import java.util.List;
import java.util.Scanner;

public class _05_Applied_Arithmetics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] array = scan.nextLine().split("\\s+");

        List<Integer> list = new ArrayList<>();

        for (String anArray : array) {
            list.add(Integer.parseInt(anArray));
        }
        label:
        while (true) {
            String x = scan.nextLine();
            switch (x) {
                case "end":
                    break label;
                case "add":
                    for (int j = 0; j < list.size(); j++) {

                        int item = list.get(j);
                        list.set(j, item + 1);
                    }
                    break;
                case "multiply":
                    for (int j = 0; j < list.size(); j++) {

                        int item = list.get(j);
                        list.set(j, item * 2);
                    }
                    break;
                case "subtract":
                    for (int j = 0; j < list.size(); j++) {

                        int item = list.get(j);
                        list.set(j, item - 1);
                    }
                    break;
                case "print":
                    for (Integer aList : list) {
                        System.out.printf("%d ", aList);
                    }
                    System.out.printf("\n");
                    break;
            }
        }
    }
}
