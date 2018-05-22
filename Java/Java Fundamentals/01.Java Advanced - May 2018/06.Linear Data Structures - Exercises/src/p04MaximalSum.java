import java.util.Scanner;

public class p04MaximalSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }

        int maxSum = Integer.MIN_VALUE;
        int indexRow = 0;
        int indexCol = 0;
        for (int i = 0; i < rows-2; i++) {
            for (int j = 0; j < cols-2; j++) {
               int sum = matrix[i][j]+ matrix[i][j+1]+matrix[i][j+2]+ matrix[i+1][j]+ matrix[i+1][j+1]+matrix[i+1][j+2]+matrix[i+2][j]+ matrix[i+2][j+1]+matrix[i+2][j+2];
               if (sum>maxSum){
                   maxSum = sum;
                   indexRow = i;
                   indexCol = j;
               }
            }
        }

        System.out.println("Sum = " + maxSum);
        System.out.println(matrix[indexRow][indexCol] + " " + matrix[indexRow][indexCol+1] + " " + matrix[indexRow][indexCol+2]);
        System.out.println(matrix[indexRow+1][indexCol] + " " + matrix[indexRow+1][indexCol+1] + " " + matrix[indexRow+1][indexCol+2]);
        System.out.println(matrix[indexRow+2][indexCol] + " " + matrix[indexRow+2][indexCol+1] + " " + matrix[indexRow+2][indexCol+2]);
    }
}