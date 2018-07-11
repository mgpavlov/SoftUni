import java.util.Arrays;
import java.util.Scanner;

public class _02_Asid {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] input = Arrays.stream(scan.nextLine()
                .split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        char[][] multi = new char[input.length][];

        for (int i = 0; i < input.length; i++) {
            Integer x = input[i];
            String stringWord = Integer.toBinaryString(x);
            while (stringWord.length() < 12) // ensure that length of word is 32
                stringWord = "0" + stringWord;


            for (Integer row = 0; row < 12; row++) {
                for (Integer col = 0; col < 12; col++) {
                    char a = stringWord.charAt(col);
                     multi[row][col] = a;
                }
            }

            // System.out.println(stringWord);
        }

        int a=2;


        for (int i = 0; i < multi.length-1; i++) {
            for (int j = 0; j < multi.length-1; j++) {

                int first = multi[i][j];
                int under = multi[i][j + 1];

                if (first == 1 && under == 0) {
                    multi[i][j] = 0;
                    multi[i][j + 1] = 1;
                }
            }
        }


        for (int i = 0; i < input.length; i++) {
            System.out.println(multi[i]);
        }

    }
}
