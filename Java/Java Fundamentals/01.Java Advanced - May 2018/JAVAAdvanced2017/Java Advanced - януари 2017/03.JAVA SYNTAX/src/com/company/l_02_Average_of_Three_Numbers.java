package com.company;

import java.util.Scanner; // 100/100

public class l_02_Average_of_Three_Numbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double first = input.nextDouble();
        double secound = input.nextDouble();
        double third = input.nextDouble();

        double total = (first + secound + third)/3;

        System.out.printf("%.2f", total);
    }
}
