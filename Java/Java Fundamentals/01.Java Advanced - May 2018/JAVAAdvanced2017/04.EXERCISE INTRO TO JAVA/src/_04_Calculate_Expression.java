import java.util.Scanner;

public class _04_Calculate_Expression {      // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Double a = Double.parseDouble(scanner.next());
        Double b = Double.parseDouble(scanner.next());
        Double c = Double.parseDouble(scanner.next());

        Double f1 = Math.pow(((Math.pow(a, 2) + b * b) / (a * a - b * b)), (a + b + c) / Math.sqrt(c));
        Double f2 = Math.pow((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 3)), a - b);
        Double diff = Math.abs((a + b + c) / 3 - (f1 + f2) / 2);

        System.out.printf("F1 result: %.2f; F2 result: %.2f; Diff: %.2f", f1, f2, diff);
    }
}
