import java.util.Scanner;

public class _13_Blur_Filter {                  //100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer blurAmount = Integer.parseInt(scanner.nextLine());
        String[] matrixDimensions = scanner.nextLine().split("\\s+");
        int rows = Integer.valueOf(matrixDimensions[0]);
        int cols = Integer.valueOf(matrixDimensions[1]);
        long[][] matrix = getMatrix(rows, cols, scanner);

        String[] coordinates = scanner.nextLine().split("\\s+");
        int x = Integer.valueOf(coordinates[0]);
        int y = Integer.valueOf(coordinates[1]);

        printBlurredPhoto(matrix, x, y, rows, cols, blurAmount);
    }

    private static void printBlurredPhoto(long[][] matrix, int x, int y, int rows, int cols, Integer blurAmount) {
        int left = Math.max(y - 1, 0);
        int right = Math.min(y + 1, cols);
        int top = Math.max(x - 1, 0);
        int bottom = Math.min(x + 1, rows);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (top <= r && r <= bottom && left <= c && c <= right) {
                    System.out.printf("%s ", matrix[r][c] + blurAmount);
                } else {
                    System.out.printf("%s ", matrix[r][c]);
                }
            }

            System.out.println();
        }

    }

    private static long[][] getMatrix(int rows, int cols, Scanner scanner) {
        long[][] matrix = new long[rows][cols];
        for (int r = 0; r < rows; r++) {
            String[] line = scanner.nextLine().split("\\s+");
            for (int c = 0; c < cols; c++) {
                matrix[r][c] = Integer.valueOf(line[c]);
            }
        }
        return matrix;
    }
}
