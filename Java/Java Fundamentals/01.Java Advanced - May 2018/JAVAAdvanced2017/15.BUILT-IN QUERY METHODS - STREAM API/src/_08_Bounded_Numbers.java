import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class _08_Bounded_Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> bounds = Arrays.stream(bf.readLine().split("\\s+"))
                .map(Integer::valueOf).collect(Collectors.toList());
        List<String> tokens = Arrays.asList(bf.readLine().split("\\s+"));

        List<Integer> filteredNums = tokens.stream()
                .filter(s -> (s != null && !s.isEmpty()))
                .map(Integer::valueOf)
                .filter(x -> (Collections.min(bounds) <= x && x <= Collections.max(bounds)))
                .collect(Collectors.toList());

        if (!filteredNums.isEmpty()) {
            System.out.println(Arrays.toString(filteredNums.toArray())
                    .replaceAll("[\\[\\],]", ""));

        }
    }
}
