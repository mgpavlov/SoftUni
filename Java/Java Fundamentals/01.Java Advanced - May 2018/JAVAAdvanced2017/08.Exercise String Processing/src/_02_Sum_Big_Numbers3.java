package src;

import java.util.Scanner;

public class _02_Sum_Big_Numbers3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String firstNum = scanner.nextLine();
        String secondNum = scanner.nextLine();

        StringBuilder result = new StringBuilder();
        int additive = 0;
        for (int i = 1; i <= Math.min(firstNum.length(), secondNum.length()); i++) {
            String numOne = firstNum.substring(firstNum.length() - i, firstNum.length() - i + 1);
            String numTwo = secondNum.substring(secondNum.length() - i, secondNum.length() - i + 1);
            int digitOne = Integer.parseInt(numOne);
            int digitTwo = Integer.parseInt(numTwo);
            result.append((digitOne + digitTwo + additive) % 10);
            if (digitOne + digitTwo + additive >= 10) {
                additive = 1;
            } else {
                additive = 0;
            }
        }

        for (int i = firstNum.length() > secondNum.length() ?
                firstNum.length() - secondNum.length() - 1 :
                secondNum.length() - firstNum.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(firstNum.length() > secondNum.length() ?
                    firstNum.substring(i, i + 1) :
                    secondNum.substring(i, i + 1));
            result.append((digit + additive) % 10);
            if (digit + additive >= 10) {
                additive = 1;
            } else {
                additive = 0;
            }
        }

        if (additive != 0) {
            result.append(additive);
        }

        while(result.toString().endsWith("0")){
            result.delete(result.length()-1,result.length());
        }

        System.out.println(result.reverse().toString());
    }
}
