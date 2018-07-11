import java.util.Scanner;

public class _05_Maximal_Sum {                       // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] matrixSize = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(matrixSize[0]);
        int cols = Integer.parseInt(matrixSize[1]);

        int[][] matrix = new int[rows][cols];
        fillMatrix(scan, rows, cols, matrix);


        Integer max = Integer.MIN_VALUE;
        int startRow = 0;
        int startCol = 0;

        for (int row = 0; row < matrix.length - 2; row++) {
            for (int col = 0; col < matrix[0].length - 2; col++) {
                Integer curentSum = matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2] +
                        matrix[row + 1][col] + matrix[row + 1][col + 1] + matrix[row + 1][col + 2] +
                        matrix[row + 2][col] + matrix[row + 2][col + 1] + matrix[row + 2][col + 2];

                if (curentSum > max) {
                    max = curentSum;
                    startRow = row;
                    startCol = col;
                }
            }
        }
        printMatrix(matrix, max, startRow, startCol);
    }

    private static void fillMatrix(Scanner scan, int rows, int cols, int[][] matrix) {
        //        for (int i = 0; i < matrixSize.length; i++) {
//            matrix[i] = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
//
//        }
        for (int i = 0; i < rows; i++) {
            String[] currentLine = scan.nextLine().split("\\s+");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(currentLine[j]);
            }
        }
    }

    private static void printMatrix(int[][] matrix, Integer max, int startRow, int startCol) {
        System.out.printf("Sum = %d%n", max);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.printf(matrix[startRow + i][startCol + j] + " ");
            }
            System.out.println();
        }
    }
}