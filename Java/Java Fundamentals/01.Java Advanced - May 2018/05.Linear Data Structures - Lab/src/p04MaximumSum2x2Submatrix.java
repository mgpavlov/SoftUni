import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class p04MaximumSum2x2Submatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstInput = sc.nextLine();
        int rows = Integer.parseInt(firstInput.split(", ")[0]);
        int columns = Integer.parseInt(firstInput.split(", ")[1]);
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            String[] row = sc.nextLine().split(", ");
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = Integer.parseInt(row[j]);
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int indexRow = 0;
        int indexCol = 0;

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                int sum = matrix[row][col] + matrix[row][col + 1] + matrix[row + 1][col] + matrix[row + 1][col + 1];
                if (sum > maxSum) {
                    maxSum = sum;
                    indexRow = row;
                    indexCol = col;
                }
            }
        }

        System.out.printf("%d %d", matrix[indexRow][indexCol],  matrix[indexRow][indexCol+1]);

        System.out.println();
        System.out.printf("%d %d", matrix[indexRow+1][indexCol],  matrix[indexRow+1][indexCol+1]);

        System.out.println();
        System.out.println(maxSum);
    }
}
