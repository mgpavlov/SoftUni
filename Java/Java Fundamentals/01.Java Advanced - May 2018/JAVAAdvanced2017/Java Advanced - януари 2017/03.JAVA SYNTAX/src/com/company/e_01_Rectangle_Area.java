package com.company;

import java.util.Scanner; // 100/100

public class e_01_Rectangle_Area {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double width = in.nextDouble();
        double height = in.nextDouble();

        System.out.printf("%.2f", width * height);
    }
}
