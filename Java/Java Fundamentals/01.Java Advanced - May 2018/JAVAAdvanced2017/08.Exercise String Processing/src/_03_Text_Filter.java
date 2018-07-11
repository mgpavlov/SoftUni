package src;

import java.util.Collections;
import java.util.Scanner;        // 100/100

public class _03_Text_Filter {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] banned = scan.nextLine().split(", ");

        String print = scan.nextLine();

        for (String aBanned : banned) {
            print = print.replaceAll(
                    aBanned,
                    String.join("", Collections.nCopies(aBanned.length(), "*")));

        }
        System.out.println(print);

    }

    private static String newString(int n, String s) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++) {
            builder.append(s);
        }

        return builder.toString();
    }
}
