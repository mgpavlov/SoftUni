import java.util.Scanner;

public class _01_Rectangle_Area {  // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Double a = scanner.nextDouble();
        Double b = scanner.nextDouble();
        Double area = a * b;

        System.out.printf("%.2f", area);
    }
}
