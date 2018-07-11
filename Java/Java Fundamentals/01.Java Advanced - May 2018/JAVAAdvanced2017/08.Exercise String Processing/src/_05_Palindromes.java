package src;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;    // 100/100

public class _05_Palindromes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("[ !?.,]");
        Set<String> order = new TreeSet<>();

        for (String word : input) {
            StringBuilder current = new StringBuilder(word);
            current.reverse();

            if (word.equals(current.toString()) && !word.equals("")) {
                order.add(word);
            }
        }
        System.out.print(order);
    }
}
