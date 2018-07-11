import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.OptionalDouble;

public class _04_Average_of_Doubles2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        OptionalDouble averageOneRow = Arrays.stream(bf.readLine().split("\\s+"))
                .filter(s -> (s!=null && !s.isEmpty()))
                .mapToDouble(Double::parseDouble)
                .average();

        if (averageOneRow.isPresent()) {
            System.out.printf("%.2f%n", averageOneRow.getAsDouble());
        } else {
            System.out.println("No match");
        }
    }
}
