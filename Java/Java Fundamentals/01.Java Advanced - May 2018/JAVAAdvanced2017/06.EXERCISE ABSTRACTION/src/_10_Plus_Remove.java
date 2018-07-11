import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;          //  0/100

public class _10_Plus_Remove {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> num = new ArrayList<>();

        while (true) {
            String currentRow = scan.nextLine();

            if (currentRow.equals("END")) {
                break;
            }
            num.add(currentRow.toLowerCase());
        }
        char[][] matrix = new char[num.size()][];
        char[][] printMatrix = new char[num.size()][];

        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = num.get(i).toCharArray();
        }
        for (int i = 0; i < matrix.length - 2; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                char c0 = matrix[i][j];
                char a = matrix[i + 1][j - 1];
                char b = matrix[i + 1][j];
                char c = matrix[i + 1][j + 1];
                char d = matrix[i + 2][j];

                try {
                    if (c0 != a || c0 != b || c0 != c || c0 != d) {
                        printMatrix[i][j] = c0;
                    }
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        PrintMatrix(printMatrix);
    }

    private static void PrintMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
