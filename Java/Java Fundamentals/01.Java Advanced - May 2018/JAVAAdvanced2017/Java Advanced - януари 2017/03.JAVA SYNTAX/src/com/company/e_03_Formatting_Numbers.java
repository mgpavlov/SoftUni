package com.company;

import java.util.Scanner; // 100/100

public class e_03_Formatting_Numbers {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int firstNumber = Integer.parseInt(input.next());
        double secondNumber = Double.parseDouble(input.next());
        double thirdNumber = Double.parseDouble(input.next());

        String numberInHex = Integer.toHexString(firstNumber).toUpperCase();
        String numberInBinary = String.format("%10s", Integer.toBinaryString(firstNumber)).replace(' ', '0');

        if (thirdNumber % 1 == 0) {
            System.out.printf("|%-10s|%s|%10.2f|%-10.3f|", numberInHex, numberInBinary,
                    secondNumber, thirdNumber);
        } else {
            System.out.printf("|%-10s|%s|%10.2f|%-10.3f|", numberInHex, numberInBinary,
                    secondNumber, thirdNumber);
        }
    }
}
