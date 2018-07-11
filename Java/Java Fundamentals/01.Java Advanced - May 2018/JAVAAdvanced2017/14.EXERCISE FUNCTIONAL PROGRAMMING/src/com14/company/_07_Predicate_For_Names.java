package com14.company;
import java.util.Scanner;  // 100/100

public class _07_Predicate_For_Names {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer limitations = Integer.parseInt(scan.nextLine());
        String[] array = scan.nextLine().split("\\s+");

        for (String word : array) {
            if (word.length() <= limitations) {
                System.out.printf("%s\n", word);
            }
        }
    }
}
