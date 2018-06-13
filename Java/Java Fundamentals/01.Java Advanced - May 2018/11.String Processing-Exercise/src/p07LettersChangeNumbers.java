import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class p07LettersChangeNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");

        double sum = 0;
        for (String string : input) {

            char firstLetter = string.charAt(0);
            char lastLetter = string.charAt(string.length()-1);
            double number = Double.parseDouble(string.substring(1, string.length()-1));

            sum += RehashNumber(firstLetter, lastLetter, number);
        }
        System.out.printf("%.2f",sum);
    }

    private static double RehashNumber(char firstLetter, char lastLetter, double number) {
        double result = 0;
        if (Character.isUpperCase(firstLetter)){
            result += number/(Character.toLowerCase(firstLetter)-96);
        }else {
            result += number*(Character.toLowerCase(firstLetter)-96);
        }

        if (Character.isUpperCase(lastLetter)){
            result -= Character.toLowerCase(lastLetter)-96;
        }else{
            result += Character.toLowerCase(lastLetter)-96;
        }

        return result;
    }
}
