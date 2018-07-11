import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class _06_Find_and_Sum_Integers {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<String> tokens = Arrays.asList(bf.readLine().split("\\s+"));

        Optional<Integer> sum = tokens.stream()
                .filter(x -> isNumber(x))
                .map(Integer::valueOf)
                .reduce((x, y) -> x + y);

        System.out.println(sum.isPresent() ? sum.get() : "No match");
    }

    private static boolean isNumber(String x) {
        if (x.isEmpty()) {
            return false;
        }

        try {
            int num = Integer.valueOf(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
