import java.util.Scanner;

public class p01RectangleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        
        double result = a*b;

        System.out.printf("%.2f", result);
    }
}
