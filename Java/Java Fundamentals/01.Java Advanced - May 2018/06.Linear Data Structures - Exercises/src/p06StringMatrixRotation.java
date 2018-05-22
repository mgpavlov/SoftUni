import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p06StringMatrixRotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String degreesString = sc.nextLine().trim().toLowerCase();

        int degrees = Integer.parseInt(degreesString.split("[()]")[1]);
        int rotationsCount = (degrees / 90) % 4;

        ArrayList<String> lines = new ArrayList<>();
        String input = sc.nextLine();

        while (!"END".equals(input))
        {
            lines.add(input);
            input = sc.nextLine();
        }

        char[][] matrix = new char[lines.size()][];
        int wordsLen = Integer.MIN_VALUE;

        for (String line : lines) {
            if (line.length() > wordsLen) {
                wordsLen = line.length();
            }
        }

        for (int row = 0; row < lines.size(); row++)
        {
            matrix[row] = rightPadding(lines.get(row), wordsLen).toCharArray();
        }

        for (int i = 0; i < rotationsCount; i++)
        {
            matrix = Rotate(matrix);
        }

        PrintMatrix(matrix);
    }

    private static void PrintMatrix(char[][] matrix)
    {
        for (char[] row : matrix) {
            for (char aRow : row) {
                System.out.print(aRow);
            }
            System.out.println();
        }
    }

    private static char[][] Rotate(char[][] words)
    {
        int wordsLen = Integer.MIN_VALUE;

        for (char[] word : words) {
            if (word.length > wordsLen) {
                wordsLen = word.length;
            }
        }

        char[][] result = new char[wordsLen][];

        for (int row = 0; row < result.length; row++)
        {
            result[row] = new char[words.length];

            for (int col = 0; col < result[row].length; col++)
            {
                result[row][col] = words[words.length - 1 - col][row];
            }
        }

        return result;
    }
    private static String rightPadding(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }
}
