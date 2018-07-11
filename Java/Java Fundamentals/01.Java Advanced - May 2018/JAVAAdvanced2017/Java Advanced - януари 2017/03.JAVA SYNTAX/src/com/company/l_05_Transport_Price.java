package com.company;

import java.util.Scanner;  // 100/100

public class l_05_Transport_Price {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int distance = input.nextInt();
        String time = input.next();

        double total = 0.00;
        if (distance > 0 && distance < 20) {
            if (time.equals("day")) {
                total = 0.7 + 0.79 * distance;
            } else if (time.equals("night")) {
                total = 0.7 + 0.9 * distance;
            }
        }
        else if (distance >= 20 && distance < 100 ) {
            total = distance * 0.09;
        } else if (distance >= 100) {
            total = distance * 0.06;
        }
        System.out.printf("%.2f", total);
    }
}
