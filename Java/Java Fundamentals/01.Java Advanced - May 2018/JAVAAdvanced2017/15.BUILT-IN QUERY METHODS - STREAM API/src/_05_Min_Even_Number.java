import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // 100/100

public class _05_Min_Even_Number {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String x = scan.nextLine();
        if (x.length() == 0) {
            System.out.printf("No match");
            return;
        }

        String[] input = x.split("\\s+");
        List<Double> numbers = new ArrayList<>();
        boolean isFound = true;

        for (String token : input) {
            numbers.add(Double.parseDouble(token));
        }
        Double min = Double.MAX_VALUE;

        for (Double current : numbers) {
            if (current % 2 == 0) {
                if (current < min) {
                    min = current;
                    isFound = false;
                }
            }
        }
        if (isFound) {
            System.out.printf("No match");
        } else {
            System.out.printf("%.2f", min);
        }
    }
}
