package com.company;

import java.util.Scanner; // 100/100

public class _01_Students_Results {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String x = scan.nextLine();

        String[] currentrow = x.split("-");
        String name = currentrow[0];
        String[] digits = currentrow[1].split(", ");

        Double jadv = Double.parseDouble(digits[0]);
        Double oop = Double.parseDouble(digits[1]);
        Double advOop = Double.parseDouble(digits[2]);
        Double average = (jadv + oop + advOop) / 3;

        System.out.printf("%1$-10s|%2$7s|%3$7s|%4$7s|%5$7s|\n"
                ,"Name","JAdv","JavaOOP","AdvOOP","Average");

        System.out.printf("%1$-10s|%2$7.2f|%3$7.2f|%4$7.2f|%5$7.4f|\n"
                ,name,jadv,oop,advOop,average);
    }
}
