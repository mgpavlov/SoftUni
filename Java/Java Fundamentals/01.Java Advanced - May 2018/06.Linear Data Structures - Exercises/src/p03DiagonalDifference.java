import java.util.Scanner;

public class p03DiagonalDifference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][]matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String[]line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(line[j]);
            }
        }
        int mainDiagonalSum = 0;
        int secondDiagonalSum = 0;
        for (int i = 0; i < n; i++) {
            mainDiagonalSum += matrix[i][i];
            secondDiagonalSum += matrix[i][n-1-i];
        }
        System.out.println(Math.abs(mainDiagonalSum-secondDiagonalSum));
    }
}
