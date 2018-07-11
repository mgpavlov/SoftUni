package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _05_Vowel_Count   // 100/100
{
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int counter = 0;
        char[] input = scan.nextLine().toLowerCase().toCharArray();

        for (char anInput : input) {
            if (isVowel(anInput)) {
                counter++;
            }
        }
        System.out.printf("Vowels: %d", counter);
    }

    private static boolean isVowel(char c) {
        List<Character> vowelList = new ArrayList<Character>();
        vowelList.add('a');
        vowelList.add('e');
        vowelList.add('i');
        vowelList.add('o');
        vowelList.add('u');
        vowelList.add('y');
        if (vowelList.contains(c))
        {
            return true;
        } else {
            return false;
        }
    }
}
