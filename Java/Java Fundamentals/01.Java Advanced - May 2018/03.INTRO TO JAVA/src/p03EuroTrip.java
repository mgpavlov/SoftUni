import java.math.BigDecimal;
import java.util.Scanner;

public class p03EuroTrip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double kg = sc.nextDouble();


        BigDecimal markRate = new BigDecimal(4210500000000.0);
        BigDecimal priceInLv = new BigDecimal(1.2*kg);

        BigDecimal priceMarks =markRate.multiply(priceInLv);

        System.out.printf("%.2f marks",priceMarks);
    }
}
