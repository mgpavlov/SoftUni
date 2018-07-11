package src;

import java.util.Scanner;   // 100/100

public class _07_Letters_Change_Numbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");

        Double sum = 0.0;

        for (int i = 0; i < input.length; i++) {
            Character firstChar = input[i].charAt(0);
            Character lastChar = input[i].charAt(input[i].length() - 1);

            Double number = Double.parseDouble(input[i].substring(1, input[i].length() - 1));


            if (firstChar.toString().toUpperCase().equals(firstChar.toString()))  // upperCase
            {
                sum += number / (firstChar - 64);  // position of letter in english alphabet
            }
            else  // lowerCase
            {
                Integer first = (int) (Character.toUpperCase(firstChar)) - 64;
                sum += number * first;
            }


            if (lastChar.toString().toUpperCase().equals(lastChar.toString()))  // upperCase
            {
                Integer last = lastChar - 64; // position of big letter in english Alhabet

                sum -= last;
            }
            else  // lowerCase
            {
                Integer last = lastChar - 96;  // position of small letter in english Alhabet
                sum += last;
            }

//            System.out.printf("%s%n", firstChar);
//            System.out.printf("%d\n", number);
//            System.out.printf("%s%n", lastChar);

        }
        System.out.printf("%.2f\n", sum);
    }
}
