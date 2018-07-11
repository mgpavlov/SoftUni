package src;

import java.util.Scanner; // 100/100

public class _07_Letters_Change_Numbers3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] inputArguments = in.nextLine().split("\\s+");
        double totalResult = 0;
        for (String word : inputArguments) {
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);
            double number = Double.parseDouble(word.substring(1, word.length() - 1));

            if (Character.toString(firstChar).equals(Character.toString(firstChar).toLowerCase())) {
                number *= firstChar - 96;
            } else {
                number /= firstChar - 64;
            }

            if (Character.toString(lastChar).equals(Character.toString(lastChar).toLowerCase())) {
                number += lastChar - 96;
            } else {
                number -= lastChar - 64;
            }

            totalResult += number;
        }

        System.out.printf("%.2f\n", totalResult);
    }
}
