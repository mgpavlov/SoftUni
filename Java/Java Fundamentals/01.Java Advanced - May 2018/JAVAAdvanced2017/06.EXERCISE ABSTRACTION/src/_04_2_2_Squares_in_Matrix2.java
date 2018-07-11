import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;   // 100/100
import java.util.Arrays;

public class _04_2_2_Squares_in_Matrix2 {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = input[0];
        int cols = input[1];

        String [][] matrix = new String[rows][cols];
        fillMatrix(reader,matrix);
        System.out.println(findSquares(matrix));

    }

    private static int findSquares(String[][] matrix) {
        int counter = 0;
        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                if (matrix[row][col].equals(matrix[row][col+ 1]) &&
                        matrix[row][col].equals(matrix[row + 1][col]) &&
                        matrix[row][col].equals(matrix[row + 1][col + 1])){
                    counter++;
                }
            }
        }
        return  counter;
    }

    private static void fillMatrix(BufferedReader reader, String[][] matrix) throws IOException {
        for (int row = 0; row < matrix.length; row++) {
            String[] remainders = reader.readLine().split(" ");
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = remainders[col];
            }
        }
    }
}
