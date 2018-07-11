package src;

import java.util.Scanner;  // 100/100

public class _04_Unicode_Characters {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        char[] input = scan.nextLine().toCharArray();
        for (char charac : input) {

            String unicode = toUnicode(charac);

            System.out.printf("%s", unicode);
        }
    }

    private static String toUnicode(char ch) {
        return String.format("\\u%04x", (int) ch);
    }
}
