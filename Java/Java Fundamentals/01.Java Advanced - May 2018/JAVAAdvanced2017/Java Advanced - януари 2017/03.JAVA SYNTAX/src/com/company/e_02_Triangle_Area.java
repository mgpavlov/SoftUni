package com.company;

import java.util.Scanner; // 100/100

public class e_02_Triangle_Area {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double Ax = in.nextDouble();
        double Ay = in.nextDouble();
        double Bx = in.nextDouble();
        double By = in.nextDouble();
        double Cx = in.nextDouble();
        double Cy = in.nextDouble();

        double area = Math.abs((Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By)) / 2);
        System.out.println((int)area);
    }
}
