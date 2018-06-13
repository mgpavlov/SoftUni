import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class exam2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[][] matrix = new String[n][n];
        String[] commands = reader.readLine().split("[, ]");

        int startRowIndex = 0;
        int startColIndex = 0;
        int food = 0;

        for (int i = 0; i < n; i++) {
            String[] lines = reader.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                if ("s".equals(lines[j])) {
                    startRowIndex = i;
                    startColIndex = j;
                }
                if ("f".equals(lines[j])) {
                    food++;
                }
                matrix[i][j] = lines[j];
            }
        }
        int snakeLength = 1;

        for (int i = 0; i < commands.length; i++) {
            if (isEaten(matrix)) {
                String result = "You win! Final snake length is " + snakeLength;
                System.out.println(result);
                return;
            }
            String command = commands[i];
            if (command.equals("up")) {
                String nextElement;
                int newRowIndex = startRowIndex - 1;
                if (newRowIndex < 0) {
                    newRowIndex = n - 1;
                }
                nextElement = matrix[newRowIndex][startColIndex];
                if (nextElement.equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                } else if (nextElement.equals("f")) {
                    snakeLength++;
                    food--;
                    matrix[newRowIndex][startColIndex] = "s";
                } else {
                    matrix[newRowIndex][startColIndex] = "s";
                }
                startRowIndex = newRowIndex;
            } else if (command.equals("down")) {
                String nextElement;
                int newRowIndex = startRowIndex + 1;
                if (newRowIndex >= n) {
                    newRowIndex = 0;
                }
                nextElement = matrix[newRowIndex][startColIndex];
                if (nextElement.equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                } else if (nextElement.equals("f")) {
                    snakeLength++;
                    food--;
                    matrix[newRowIndex][startColIndex] = "s";
                } else {
                    matrix[newRowIndex][startColIndex] = "s";
                }
                startRowIndex = newRowIndex;
            } else if (command.equals("right")) {
                String nextElement;
                int newColIndex = startColIndex + 1;
                if (newColIndex >= n) {
                    newColIndex = 0;
                }
                nextElement = matrix[startRowIndex][newColIndex];
                if (nextElement.equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                } else if (nextElement.equals("f")) {
                    snakeLength++;
                    food--;
                    matrix[startRowIndex][newColIndex] = "s";
                } else {
                    matrix[startRowIndex][newColIndex] = "s";
                }
                startColIndex = newColIndex;
            } else if (command.equals("left")) {
                String nextElement;
                int newColIndex = startColIndex - 1;
                if (newColIndex < 0) {
                    newColIndex = n - 1;
                }
                nextElement = matrix[startRowIndex][newColIndex];
                if (nextElement.equals("e")) {
                    System.out.println("You lose! Killed by an enemy!");
                    return;
                } else if (nextElement.equals("f")) {
                    snakeLength++;
                    food--;
                    matrix[startRowIndex][newColIndex] = "s";
                } else {
                    matrix[startRowIndex][newColIndex] = "s";
                }
                startColIndex = newColIndex;
            }
        }
        if (isEaten(matrix)) {
            String result = "You win! Final snake length is " + snakeLength;
            System.out.println(result);

        } else {
            String result = "You lose! There is still " + food + " food to be eaten.";
            System.out.println(result);
        }

    }

    private static boolean isEaten(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("f")) {
                    return false;
                }
            }
        }
        return true;
    }
}
