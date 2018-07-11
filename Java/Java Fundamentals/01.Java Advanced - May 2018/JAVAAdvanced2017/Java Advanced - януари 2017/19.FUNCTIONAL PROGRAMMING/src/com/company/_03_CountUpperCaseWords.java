package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _03_CountUpperCaseWords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String ivan = scanner.nextLine();
        String[] numbers = ivan.split(" ");

        Integer count = sumNumbers(Arrays.asList(numbers));
        System.out.printf("%d%n",count);
        printt(Arrays.asList(numbers));


    }

    private static void printt(List<String> strings) {
        String  curent="";

        for (String number : strings) {
            char[] charArray = number.toCharArray();
            for (char aCharArray : charArray) {

                boolean x = Character.isUpperCase(charArray[0]);
                if (x) {
                    System.out.printf("%s\n", number);
                    break;
                }
            }

        }
    }

    private static Integer sumNumbers(List<String> strings) {

        Integer count = 0;

        for (String number : strings) {
            char[] charArray = number.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char charr = charArray[i];
                boolean x = Character.isUpperCase(charArray[0]);

                if (x){
                    count++;
                    break;
                }
            }

        }
        return count;
    }
}
