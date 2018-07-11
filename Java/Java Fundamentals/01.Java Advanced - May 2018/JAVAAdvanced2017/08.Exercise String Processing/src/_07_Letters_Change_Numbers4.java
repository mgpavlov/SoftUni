package src;

import java.util.Scanner; // 100/100

public class _07_Letters_Change_Numbers4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("\\s+");

        double result = 0;
        for (int i = 0; i < input.length; i++) {
            double number = Integer.parseInt(input[i].substring(1, input[i].length() - 1));
            if (input[i].charAt(0) - 'a' >= 0) {
                number *= input[i].charAt(0) - 'a' + 1;
            } else {
                number /= input[i].charAt(0) - 'A' + 1;
            }

            if (input[i].charAt(input[i].length() - 1) - 'a' >= 0) {
                number += input[i].charAt(input[i].length() - 1) - 'a' + 1;
            } else {
                number -= input[i].charAt(input[i].length() - 1) - 'A' + 1;
            }
            result += number;
        }

        System.out.printf("%.2f", result);
    }
}
