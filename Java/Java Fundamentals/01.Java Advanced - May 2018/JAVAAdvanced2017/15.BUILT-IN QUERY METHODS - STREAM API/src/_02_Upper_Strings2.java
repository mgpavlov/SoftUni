import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.List;

public class _02_Upper_Strings2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<String> strings = Arrays.asList(bf.readLine().split("\\s+"));

        strings.stream()
                .map(String::toUpperCase)
                .forEach(s -> System.out.print(s + " "));

    }
}
