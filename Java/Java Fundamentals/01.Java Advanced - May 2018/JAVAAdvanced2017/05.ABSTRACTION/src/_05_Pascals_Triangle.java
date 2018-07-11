import java.util.Arrays;
import java.util.Scanner;  // 60/100

public class _05_Pascals_Triangle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = Integer.parseInt(scan.nextLine());

        long[][] pascalTriangle = new long[n][];
        int currentWidth = 1;

        for (int currentHeight = 0; currentHeight < n; currentHeight++) {
            pascalTriangle[currentHeight] = new long[currentWidth];
            long[] currentRow = pascalTriangle[currentHeight];
            currentWidth++;
            currentRow[0] = 1;
            currentRow[currentRow.length - 1] = 1;
            if (currentRow.length > 2)
                for (int i = 1; i < currentRow.length - 1; i++) {
                    long[] previousRow = pascalTriangle[currentHeight - 1];
                    long previousRowSum = previousRow[i] + previousRow[i - 1];
                    currentRow[i] = previousRowSum;
                }
        }

        for (long[] aPascalTriangle : pascalTriangle) {
            System.out.println(Arrays.toString(aPascalTriangle).replace("[", "").replace("]", "").replace(",", ""));
        }
    }
}
