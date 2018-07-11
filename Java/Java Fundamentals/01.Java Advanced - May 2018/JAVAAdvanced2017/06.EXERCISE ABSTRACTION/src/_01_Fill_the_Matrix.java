import java.util.Scanner;

public class _01_Fill_the_Matrix {  //100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split(",\\s+");
        Integer rows = Integer.parseInt(dimensions[0]);
        Integer[][] matrix = new Integer[rows][];

        if (dimensions[1].equals("A")) {

            matrix = calcilateTypeA(rows);
        }
        if (dimensions[1].equals("B")) {
            matrix = calcilateTypeB(rows);
        }
        printMatrix(matrix, rows);
    }

    private static void printMatrix(Integer[][] matrix, Integer rows) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static Integer[][] calcilateTypeB(Integer n) {

        Integer[][] matrix = new Integer[n][n];
        Integer counter = 1;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < n; j++) {
                    matrix[j][i] = counter++;
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    matrix[j][i] = counter;
                    counter++;
                }
            }
        }
        return matrix;
    }

    private static Integer[][] calcilateTypeA(Integer rows) {
        Integer[][] matrix = new Integer[rows][rows];
        Integer counter = 1;
        for (int row = 0; row < rows; row++) {
            for (int colum = 0; colum < rows; colum++) {

                matrix[colum][row] = counter++;
            }
        }
        return matrix;
    }
}
