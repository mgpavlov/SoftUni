import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class p05MinEvenNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = Arrays.asList(reader.readLine().split("\\s+"));

        OptionalDouble minEvenNum = input.stream().filter(x->!x.isEmpty()).mapToDouble(Double::parseDouble).filter(x->x%2==0).min();

        if (minEvenNum.isPresent()){
            System.out.printf("%.2f", minEvenNum.getAsDouble());
        }else {
            System.out.println("No match");
        }
    }
}

/*import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Optional;
        import java.util.OptionalDouble;

public class p05MinEvenNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> input = Arrays.asList(reader.readLine().split("\\s+"));

        Optional<Double> minEvenNum = input.stream().filter(x->!x.isEmpty()).map(Double::parseDouble).filter(x->x%2==0).min(Double::compareTo);

        if (minEvenNum.isPresent()){
            System.out.printf("%.2f", minEvenNum.get());
        }else {
            System.out.println("No match");
        }
    }
}*/

