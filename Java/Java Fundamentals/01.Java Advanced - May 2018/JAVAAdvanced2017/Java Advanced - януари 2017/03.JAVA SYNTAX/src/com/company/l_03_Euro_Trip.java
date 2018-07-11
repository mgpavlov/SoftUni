package com.company;

import java.math.BigDecimal;
import java.util.Scanner;

public class l_03_Euro_Trip {    // 100/100
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double quantity = Double.parseDouble(input.nextLine());
        double pricePerKilo = 1.20;
        BigDecimal priceInLevs = new BigDecimal(pricePerKilo * quantity);
        BigDecimal exchangeRate = new BigDecimal(4210500000000.00);
        BigDecimal marksNeeded = exchangeRate.multiply(priceInLevs);

        System.out.printf("%.2f marks", marksNeeded);
    }
}
