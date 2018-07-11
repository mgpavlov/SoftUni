package com.company;

import java.math.BigInteger;   // 100/100
import java.util.Scanner;

public class _08_Multiply_Big_Number {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        String second = in.nextLine();

        BigInteger result = new BigInteger(first).multiply(new BigInteger(second));
        System.out.println(result);
    }
}
