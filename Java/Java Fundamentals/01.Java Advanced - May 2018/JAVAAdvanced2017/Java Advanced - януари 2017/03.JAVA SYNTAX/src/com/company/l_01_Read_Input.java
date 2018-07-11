package com.company;

import java.util.Scanner;

public class l_01_Read_Input {  // 100/100

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String inputString = input.next();
        String inputString1 = input.next();

        int first = input.nextInt();
        int secound = input.nextInt();
        int third = input.nextInt();
        input.nextLine();
        int total = first + secound + third;

        String endString = input.nextLine();

        System.out.println(inputString + " " + inputString1 + " " + endString + " " + total);
    }
}
