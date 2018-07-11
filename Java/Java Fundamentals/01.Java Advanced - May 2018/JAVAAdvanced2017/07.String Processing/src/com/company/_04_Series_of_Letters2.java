package com.company;

import java.util.Scanner;  // 100/100

public class _04_Series_of_Letters2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] input = scanner.nextLine().toCharArray();
        StringBuilder output = new StringBuilder();

        int state = 0;
        int i = 0;
        char prev = input[0];

        while (i < input.length) {
            switch (state) {
                case 0:     //initial state
                    state = 1;
                    prev = input[i];
                    break;
                case 1:    // Found a new letter
                    output.append(input[i-1]);
                    if (input[i] == prev) {
                        state = 2;
                    }
                    prev = input[i];
                    break;
                case 2:   // Found the same letter
                    if (input[i] != prev) {
                        state = 1;
                    }
                    prev = input[i];
                    break;
            }
            ++i;
        }
        if(input[i-2] != prev){
            output.append(prev);
        }
        System.out.println(output.toString());
    }
}
