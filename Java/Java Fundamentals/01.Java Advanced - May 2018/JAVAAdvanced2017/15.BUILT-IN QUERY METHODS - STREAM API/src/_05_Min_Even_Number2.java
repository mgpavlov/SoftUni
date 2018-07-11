import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.OptionalDouble;

public class _05_Min_Even_Number2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        OptionalDouble minEven = Arrays.stream(bf.readLine().split("\\s+"))
                .filter(s -> (s != null && !s.isEmpty()))
                .mapToDouble(Double::valueOf)
                .filter(x -> x % 2 == 0)
                .min();

        if (minEven.isPresent()) {
            System.out.printf("%.2f", minEven.getAsDouble());
        } else {
            System.out.println("No match");
        }
    }
}
