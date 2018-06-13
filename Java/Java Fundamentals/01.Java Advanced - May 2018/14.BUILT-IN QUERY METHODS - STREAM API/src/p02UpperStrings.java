import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p02UpperStrings {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.stream(reader.readLine().split("\\s+")).collect(Collectors.toList());

        input.stream().map(String::toUpperCase).forEach(x -> System.out.print(x + " "));
    }
}
