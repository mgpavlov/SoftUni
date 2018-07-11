package com.company;

import java.util.Scanner; // 100/100

public class e_04_Calculate_Expression {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double total1 = Math.pow(((Math.pow(a, 2) + Math.pow(b, 2)) / (Math.pow(a, 2) - Math.pow(b, 2))), ((a + b + c) / Math.sqrt(c)));
        double total2 = Math.pow((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 3)), (a - b));

        double avgABC = (a + b + c) / 3;
        double avgF1F2 = (total1 + total2) / 2;

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f", total1, total2, Math.abs(avgABC - avgF1F2));

    }
}
