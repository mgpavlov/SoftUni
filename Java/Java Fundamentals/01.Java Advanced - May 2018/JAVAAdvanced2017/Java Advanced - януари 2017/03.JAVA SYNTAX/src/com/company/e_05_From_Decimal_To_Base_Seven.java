package com.company;

import java.util.ArrayList;
import java.util.Scanner;    //  100/100

public class e_05_From_Decimal_To_Base_Seven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        ArrayList<Integer> result = new ArrayList<>();

        int remainder = 0;
        boolean finish = false;

        while (!finish) {
            if (number == 0) {
                finish = true;
            } else {
                remainder = number % 7;
                number = number / 7;
                result.add(remainder);
            }
            remainder = 0;
        }
        for (int i = result.size() - 1; i >= 0; i--) {
            int a = result.get(i);
            System.out.print(a);
        }
        System.out.println();
    }
}
