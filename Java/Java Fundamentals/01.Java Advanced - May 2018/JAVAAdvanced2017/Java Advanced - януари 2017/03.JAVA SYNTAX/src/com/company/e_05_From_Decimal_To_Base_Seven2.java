package com.company;

import java.util.Scanner;

public class e_05_From_Decimal_To_Base_Seven2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        String result = "";
        while (n != 0)
        {
            result += n % 7;
            n /= 7;
        }


        System.out.println(new StringBuilder(result).reverse());
    }
}
