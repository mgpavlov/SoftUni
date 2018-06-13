import java.util.Scanner;

public class p03SumMatrixElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstInput = sc.nextLine();
        int rows = Integer.parseInt(firstInput.split(", ")[0]);
        int columns = Integer.parseInt(firstInput.split(", ")[1]);


        int sum = 0;
        for (int i = 0; i < rows; i++) {
            String[] line = sc.nextLine().split(", ");
            for (int j = 0; j < line.length; j++) {
                sum += Integer.parseInt(line[j]);
            }
        }
        System.out.println(rows);
        System.out.println(columns);
        System.out.println(sum);
    }
}
