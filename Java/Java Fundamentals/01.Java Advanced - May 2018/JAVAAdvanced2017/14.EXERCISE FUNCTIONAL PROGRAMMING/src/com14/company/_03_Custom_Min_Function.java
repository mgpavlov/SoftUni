package com14.company;

import java.util.*; // 100/100

public class _03_Custom_Min_Function {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] array = scan.nextLine().split("\\s+");

        List<Integer> list = new ArrayList<>();

        for (String anArray : array) {
            list.add(Integer.parseInt(anArray));
        }

        System.out.print(Collections.min(list));


    }
}
