import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class p06StringMatrixRotation2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        int degrees = Integer.parseInt(command.split("[)(]")[1]);
        List<String> words = new ArrayList<>();
        int longestWord = 0;
        while (true) {
            String input = reader.readLine();
            if ("END".equals(input)) {
                break;
            }
            words.add(input);
            if (input.length() > longestWord) {
                longestWord = input.length();
            }
        }
        char[][] matrix = new char[words.size()][longestWord];

        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < longestWord; j++) {
                if (j < words.get(i).length()) {
                    matrix[i][j] = words.get(i).charAt(j);
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

        int rotationTimes = (degrees / 90) % 4;
        for (int i = 0; i < rotationTimes; i++) {
            matrix = Rotate90(matrix);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

    private static char[][] Rotate90(char[][] matrix) {
        char[][] newMatrix = new char[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[matrix.length - 1 - j][i];
            }
        }
        return newMatrix;
    }
}
