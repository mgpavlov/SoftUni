package com.company;

import java.util.Scanner; // 100/100

public class l_09_Calculate_Triangle_Area_Method {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        String[] splitStrings = input.split(" ");
        String base = splitStrings[0];
        String height = splitStrings[1];

        Double area = areaCalculate(base, height);

        System.out.printf("Area = %.2f", area);
    }

    private static Double areaCalculate(String base, String height) {
        return (Double.parseDouble(base) * Double.parseDouble(height))/2;
    }
}
