import java.util.Scanner;

public class p01FillTheMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(", ");
        int n = Integer.parseInt(input[0]);
        String type = input[1];
        int[][] matrix = new int[n][n];
        if (type.equals("A")){
            fillMatrixtypeA(n, matrix);
        }else {
            fillMatrixtypeB(n, matrix);
        }
    }

    private static void fillMatrixtypeB(int n, int[][] matrix) {
        int value = 1;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                if (col%2 == 0){
                    matrix[row][col] = value;
                    value++;
                }else{
                    matrix[n-1-row][col] = value;
                    value++;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void fillMatrixtypeA(int n, int[][] matrix) {
        int value = 1;
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < n; row++) {
                matrix[row][col] = value;
                value++;
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
