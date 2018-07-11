import java.util.Scanner;  // 100/100

public class _03_Sum_Matrix_Elements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split(", ");

        Integer rows = Integer.parseInt(dimensions[0]);
        Integer columns = Integer.parseInt(dimensions[1]);
        Integer sum = 0;

        for (int row = 0; row < rows; row++) {
            String[] reminder = scan.nextLine().split(", ");
            for (int col = 0; col < columns; col++) {
                sum += Integer.parseInt(reminder[col]);
            }
        }
        System.out.printf("%d\n", rows);
        System.out.printf("%d\n", columns);
        System.out.printf("%d\n", sum);
    }
}
