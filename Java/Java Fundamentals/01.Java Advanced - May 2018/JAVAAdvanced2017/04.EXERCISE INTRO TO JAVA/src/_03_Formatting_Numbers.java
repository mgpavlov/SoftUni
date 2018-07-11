import java.util.Scanner;

public class _03_Formatting_Numbers {              // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer a = Integer.parseInt(scanner.next());
        Double b = Double.parseDouble(scanner.next());
        Double c = Double.parseDouble(scanner.next());

        String numberInHex = Integer.toHexString(a).toUpperCase();
        String numberInBinary = String.format("%10s", Integer.toBinaryString(a)).replace(' ', '0');

        if (c % 1 == 0) {
            System.out.printf("|%-10s|%s|%10.2f|%-10.3f|", numberInHex, numberInBinary, b, c);
        } else {
            System.out.printf("|%-10s|%s|%10.2f|%-10.3f|", numberInHex, numberInBinary, b, c);
        }
    }
}
