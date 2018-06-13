import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p02SumBigNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String longerDigit = CutPaddingZero(reader.readLine());
        String shorterDigit = CutPaddingZero(reader.readLine());
        String temp = "";
        if (longerDigit.length()<shorterDigit.length()){
            temp = longerDigit;
            longerDigit = shorterDigit;
            shorterDigit = temp;
        }
        StringBuilder result = new StringBuilder();
        int j = 0;
        int remainder = 0;
        while (longerDigit.length()-1-j >= 0){
            int firstNum = Integer.parseInt(String.valueOf(longerDigit.charAt(longerDigit.length()-1-j)));
            int secondNum = 0;
            if (shorterDigit.length()-1-j>=0){
                secondNum = Integer.parseInt(String.valueOf(shorterDigit.charAt(shorterDigit.length()-1-j)));
            }
            int sum = firstNum + secondNum + remainder;
            result.append(sum%10);
            remainder = sum/10;
            j++;
        }
        if (remainder!= 0 ){
            System.out.println(result.append(remainder).reverse().toString());
        }else{
            System.out.println(result.reverse().toString());
        }
    }

    private static String CutPaddingZero(String string) {
        while (string.startsWith("0")){
            string = string.substring(1);
        }
        return string;
    }
}
