import java.util.Scanner;

public class _02_Triangle_Area {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double Ax = scanner.nextInt();
        double Ay = scanner.nextInt();
        double Bx = scanner.nextInt();
        double By = scanner.nextInt();
        double Cx = scanner.nextInt();
        double Cy = scanner.nextInt();

        double area = (Ax * (By - Cy) + Bx * (Cy - Ay) + Cx * (Ay - By)) / 2;

        System.out.printf("%d", Math.abs((int) area));

    }
}
