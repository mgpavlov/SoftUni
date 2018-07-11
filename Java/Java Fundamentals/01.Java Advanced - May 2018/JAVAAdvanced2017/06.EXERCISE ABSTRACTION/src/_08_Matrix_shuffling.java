import java.util.Scanner;

public class _08_Matrix_shuffling {
    public static void main(String[] args) {         // 100/100
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split("\\s+");
        Integer row = Integer.parseInt(dimensions[0]);
        Integer col = Integer.parseInt(dimensions[1]);
        String[][] matrix = new String[row][col];

        for (int i = 0; i < row; i++) {
            matrix[i] = scan.nextLine().split(" ");
        }

        while (true) {
            String[] currentCommand = scan.nextLine().split("\\s+");
            if (currentCommand[0].equals("END")) {
                break;
            }
            if (currentCommand[0].equals("swap") && currentCommand.length == 5) {

                Integer startRow = Integer.parseInt(currentCommand[1]);
                Integer startColum = Integer.parseInt(currentCommand[2]);
                Integer endRow = Integer.parseInt(currentCommand[3]);
                Integer endColum = Integer.parseInt(currentCommand[4]);

                if (startRow > row || endRow > row || startColum > col || endColum > col) {
                    System.out.printf("Invalid input!\n");
                } else {
                    String tempopary = "";

                    tempopary = matrix[startRow][startColum];
                    matrix[startRow][startColum] = matrix[endRow][endColum];
                    matrix[endRow][endColum] = tempopary;

                    printMatrix(matrix);
                }

            } else {
                System.out.printf("Invalid input!\n");
            }
        }

    }

    private static void printMatrix(String[][] matrix) {
        for (String[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(aMatrix[j] + " ");
            }
            System.out.println();
        }
    }
}