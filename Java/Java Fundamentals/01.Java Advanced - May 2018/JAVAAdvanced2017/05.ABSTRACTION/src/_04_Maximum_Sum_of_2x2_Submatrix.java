import java.util.Scanner;

public class _04_Maximum_Sum_of_2x2_Submatrix { // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split(",\\s+");
        Integer rows = Integer.parseInt(dimensions[0]);
        Integer cols = Integer.parseInt(dimensions[1]);
        Integer[][] matrix = new Integer[rows][cols];

        for (int i = 0; i < rows; i++) {
            String[] currentRow = scan.nextLine().split(",\\s+");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(currentRow[j]);
            }
        }
        Integer maxSum = Integer.MIN_VALUE;
        int startRow = 0;
        int startCol = 0;
        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                int currentSum = matrix[i][j] + matrix[i][j + 1] + matrix[i + 1][j] + matrix[i + 1][j + 1];

                if (currentSum > maxSum) {
                    maxSum = currentSum;

                    startRow = i;
                    startCol = j;
                }
            }
        }
        for (int i = startRow; i <= startRow + 1; i++) {
            for (int j = startCol; j < startCol + 1; j++) {
                System.out.println(matrix[i][j] + " "+matrix[i][j+1]);
            }
        }
        System.out.println(maxSum);

    }
}
