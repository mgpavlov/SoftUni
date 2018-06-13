import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p101Shockwave {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String mat = reader.readLine();
        int n = Integer.valueOf(mat.split("\\s+")[0]);
        int m = Integer.valueOf(mat.split("\\s+")[1]);

        int[][] matrix = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = 0;
            }
        }

        while(true){
            String input = reader.readLine();
            if ("Here We Go".equals(input)){
                break;
            }
            List<Integer> coordinates = Arrays.stream(input.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
            int x1 = coordinates.get(0);
            int y1 = coordinates.get(1);
            int x2 = coordinates.get(2);
            int y2 = coordinates.get(3);
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    matrix[i][j]+=1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
