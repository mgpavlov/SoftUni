import java.util.Scanner;

public class _04_2_2_Squares_in_Matrix {     // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][] matrix = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i] = scan.nextLine().split("\\s+");
        }

        Integer counter = 0;

        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < cols - 1; j++) {
                if (matrix[i][j].equals(matrix[i][j + 1])
                        && matrix[i][j].equals(matrix[i + 1][j])
                        && matrix[i][j].equals(matrix[i + 1][j + 1])) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }
}