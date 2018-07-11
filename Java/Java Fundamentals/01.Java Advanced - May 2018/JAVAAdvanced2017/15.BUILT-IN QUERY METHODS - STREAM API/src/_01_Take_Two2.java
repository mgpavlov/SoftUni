import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _01_Take_Two2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> nums = Arrays.stream(bf.readLine().split("\\s+"))
                .map(Integer::valueOf).collect(Collectors.toList());

        Predicate<Integer> filter = n -> 10 <= n && n <= 20;

        Consumer<Integer> print = n -> System.out.print(n + " ");
        nums.stream()
                .filter(filter)
                .distinct()
                .limit(2)
                .forEach(print);
    }
}
