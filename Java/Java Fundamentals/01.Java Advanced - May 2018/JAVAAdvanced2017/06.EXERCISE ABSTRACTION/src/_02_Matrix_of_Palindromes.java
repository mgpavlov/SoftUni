import java.util.Scanner;

public class _02_Matrix_of_Palindromes {        // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        Integer rows = Integer.parseInt(input[0]);
        Integer colums = Integer.parseInt(input[1]);
        String[][] matrix = new String[rows][colums];

        char startingChar = 'a';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                matrix[i][j] = startingChar + "" + (char) (startingChar + j) + "" + startingChar;
            }
            startingChar++;
        }
        printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println();
        }
    }
}
