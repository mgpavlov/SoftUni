import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100

public class _03_Diagonal_Difference3 {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[][] matrix = new int[size][size];

        fillMatrix(reader,matrix);
        int primaryDiagonal = getSumOfPrimaryDiagonal(matrix);
        int secondaryDiagonal = getSumOfSecondaryDiagonal(matrix);
        int result = Math.abs(primaryDiagonal - secondaryDiagonal);
        System.out.println(result);

    }

    private static int getSumOfSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                if (rows + cols == matrix.length - 1 || rows - cols == matrix.length - 1){//owr == i and col == n-1-i.
                    sum += matrix[rows][cols];
                }
            }
        }
        return sum;
    }

    private static int getSumOfPrimaryDiagonal(int[][] matrix) {
        int sum = 0;
        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[rows].length; cols++) {
                if (rows == cols){// row == col == i
                    sum += matrix[rows][cols];
                }
            }
        }
        return sum;
    }

    private static void fillMatrix(BufferedReader reader,int[][] matrix) throws IOException {
        for (int row = 0; row < matrix.length; row++) {
            String[] remainders = reader.readLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = Integer.parseInt(remainders[col]);
            }
        }
    }
}
