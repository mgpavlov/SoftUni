import java.util.Scanner;

public class p05MatrixShuffling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][] matrix = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = line[j];
            }
        }

        String command = sc.nextLine();
        while(!"END".equals(command)){
            String [] indexes = command.split(" ");

            if (validate(command, rows, cols)){
                int row1 = Integer.parseInt(indexes[1]);
                int col1 = Integer.parseInt(indexes[2]);

                int row2 = Integer.parseInt(indexes[3]);
                int col2 = Integer.parseInt(indexes[4]);

                String cell1 = matrix[row1][col1];
                matrix[row1][col1] = (matrix[row2][col2]);
                matrix[row2][col2] = cell1;
                printMatrix(matrix);
            }else{
                System.out.println("Invalid input!");
            }
            command = sc.nextLine();
        }

    }

    private static boolean validate(String command, int rows, int cols) {
        String[] indexes = command.split(" ");
        if (!command.startsWith("swap")){
            return false;
        }
        if (indexes.length != 5){
            return false;
        }
        for (int i = 1; i < 5; i++) {
            if (!isInteger(indexes[i])){
                return false;
            }
        }
        if (Integer.parseInt(indexes[1]) > rows-1 || Integer.parseInt(indexes[1]) < 0 )
            return false;
        if (Integer.parseInt(indexes[2]) > cols-1 || Integer.parseInt(indexes[2]) < 0 )
            return false;
        if (Integer.parseInt(indexes[3]) > rows-1 || Integer.parseInt(indexes[3]) < 0 )
            return false;
        if (Integer.parseInt(indexes[4]) > cols-1 || Integer.parseInt(indexes[4]) < 0 )
            return false;


        return true;
    }

    private static boolean isInteger(String s) {
        boolean isValidInteger = false;
        try
        {
            Integer.parseInt(s);

            // s is a valid integer

            isValidInteger = true;
        }
        catch (NumberFormatException ex)
        {
            // s is not an integer
        }

        return isValidInteger;
    }

    private static void printMatrix(String [][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print (matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

}
