package com.company;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Scanner;         // 100/100

public class l_07_Product_of_Numbers_N_M {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BigInteger result = BigInteger.valueOf(1);
        Integer num1 = scan.nextInt();
        Integer num2 = scan.nextInt();
        for (int i = num1; i <= num2; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        System.out.print(MessageFormat.format("product[{0}..{1}] = {2}", num1, num2, result.toString().replaceAll(",", "")));
    }
}
