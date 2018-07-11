import java.math.BigDecimal;
import java.util.Scanner;

public class _03_Euro_Trip {                      // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double quantity = Double.parseDouble(scanner.next());
        final double pricePerKilograms = 1.20;

        BigDecimal price = new BigDecimal(pricePerKilograms * quantity);

        BigDecimal exchangeRate = new BigDecimal("4210500000000");
        BigDecimal marksNeeded = exchangeRate.multiply(price);

        System.out.printf("%.2f marks", marksNeeded);
    }
}
