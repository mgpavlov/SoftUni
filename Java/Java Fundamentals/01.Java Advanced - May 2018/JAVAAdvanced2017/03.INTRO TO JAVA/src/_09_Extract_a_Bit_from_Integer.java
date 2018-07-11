import java.util.Scanner;  // 100/100

public class _09_Extract_a_Bit_from_Integer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int p = scanner.nextInt();

        if (p >= n) {
            System.out.printf("0");
        } else {
            String binar = Integer.toBinaryString(n);
            String binary = new StringBuilder(binar).reverse().toString();

            if (binary.length() < p) {
                System.out.printf("%s", 0);
            } else {
                Character needNumber = binary.charAt(p);
                System.out.printf("%s", needNumber);
            }
        }
    }
}
