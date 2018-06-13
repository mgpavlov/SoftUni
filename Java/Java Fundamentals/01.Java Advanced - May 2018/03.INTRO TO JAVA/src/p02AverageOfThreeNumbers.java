import java.util.Scanner;

public class p02AverageOfThreeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double first = sc.nextDouble();
        double sec  = sc.nextDouble();
        double third = sc.nextDouble();

        System.out.printf("%.2f", (first+sec+third)/3);
    }
}
