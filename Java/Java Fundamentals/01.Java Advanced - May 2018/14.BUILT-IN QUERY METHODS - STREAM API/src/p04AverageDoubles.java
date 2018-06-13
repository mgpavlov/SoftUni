import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p04AverageDoubles {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = Arrays.asList(reader.readLine().split("\\s+"));

        OptionalDouble average = input.stream().filter(x->!x.isEmpty()).mapToDouble(Double::parseDouble).average();

        if (average.isPresent()){
            System.out.printf("%.2f",average.getAsDouble());
        }else {
            System.out.println("No match");
        }


    }
}
