package com14.company;

import java.util.ArrayList;   // 100/100
import java.util.List;
import java.util.Scanner;

public class _06_Reverse_And_Exclude {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] array = scan.nextLine().split("\\s+");
        Integer searched = Integer.parseInt(scan.nextLine());

        List<Integer> list = new ArrayList<>();

        for (String anArray1 : array) {
            Integer anArray = Integer.parseInt(anArray1);

            if (anArray % searched != 0) {
                list.add(anArray);
            }
        }
        for (int i = list.size(); i > 0; i--) {
            System.out.printf("%d ", list.get(i - 1));
        }
    }
}
