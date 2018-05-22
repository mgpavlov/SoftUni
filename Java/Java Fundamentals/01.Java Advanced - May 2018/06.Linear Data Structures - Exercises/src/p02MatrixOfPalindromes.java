import java.util.Scanner;

public class p02MatrixOfPalindromes {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] input = sc.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][]matrix = new String[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.printf("%s%s%s ", (char)('a'+row), (char)('a'+row+col), (char)('a'+row));
            }
            System.out.println();
        }
    }
}
