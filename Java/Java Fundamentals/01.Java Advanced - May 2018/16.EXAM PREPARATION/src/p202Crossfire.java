import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p202Crossfire {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] matrixSize = reader.readLine().split("\\s+");
        int rows = Integer.parseInt(matrixSize[0]);
        int cols = Integer.parseInt(matrixSize[1]);
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<ArrayList<Integer>> matrix1 = new ArrayList<>();

        int num = 1;
        for (int i = 0; i < rows; i++) {
            matrix.add(new ArrayList<>());
            matrix1.add(new ArrayList<>());
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.get(i).add(num);
                matrix1.get(i).add(num);
                num++;
            }
        }

        while (true) {
            String input = reader.readLine();
            if (input.equals("Nuke it from orbit")) {
                break;
            }
            String[] tokens = input.split("\\s+");
            int y = Integer.parseInt(tokens[0]);
            int x = Integer.parseInt(tokens[1]);
            int r = Integer.parseInt(tokens[2]);

            int startXindex = x - r ;
            int endXindex = x + r;

            int startYindex = y - r ;
            int endYindex = y + r;


            for (int i = startXindex; i <= endXindex; i++) {
                if (isInMatrix(y, i, matrix)) {
                    matrix.get(y).set(i, -1);
                }
            }
            for (int i = startYindex; i <= endYindex; i++) {
                if (isInMatrix(i, x, matrix)) {
                    matrix.get(i).set(x, -1);
                }
            }

            for (ArrayList<Integer> list : matrix) {
                list.removeAll(Arrays.asList((new Integer[]{-1})));
            }
            matrix.removeAll(Arrays.asList(new ArrayList<Integer>()));
        }
        for (ArrayList<Integer> line : matrix) {
            for (Integer integer : line) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInMatrix(Integer currentRow, Integer currentCol, ArrayList<ArrayList<Integer>> numberMatrix) {
        return currentRow >= 0 && currentRow < numberMatrix.size() && currentCol >= 0 && currentCol < numberMatrix.get(currentRow).size();
    }
}
