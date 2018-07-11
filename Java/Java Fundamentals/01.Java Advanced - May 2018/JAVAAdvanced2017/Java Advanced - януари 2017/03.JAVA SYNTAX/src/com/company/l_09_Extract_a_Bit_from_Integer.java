package com.company;

import java.util.Scanner;  // 40/100

public class l_09_Extract_a_Bit_from_Integer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] area = in.nextLine().split(" ");

        String Number = area[1];
        Integer p = Integer.parseInt(area[1]);
        String convert = String.format("%016d", Integer.parseInt(Number));

        int Numberl = Integer.parseInt(convert);
        int mask = 1;
        mask = mask << p;
        int secondmask = Numberl & mask;

        if (secondmask != 0) {
            System.out.printf("%d", 1);

        } else {
            System.out.printf("%d", 0);
        }
    }
}
