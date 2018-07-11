import java.util.Arrays;
import java.util.Scanner;

public class _03_Diagonal_Difference {              // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = Integer.parseInt(scan.nextLine());
        int[][] matrix = new int[n][n];

        Integer primeryDiagonal = 0;
        Integer secondaryDiagonal = 0;

        for (int i = 0; i < matrix.length; i++) {
            int[] matrixValues = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            matrix[i] = matrixValues;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j == i) {
                    primeryDiagonal += matrix[i][j];
                }
                if (j == n - 1 - i) {
                    secondaryDiagonal += matrix[i][j];
                }
            }
        }
        System.out.printf("%d", Math.abs(primeryDiagonal - secondaryDiagonal));
    }
}
