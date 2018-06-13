import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p203Earthquake2_Deque {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(reader.readLine());

        Deque<Deque<Integer>> data = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            Deque<Integer> input = Arrays.stream(reader.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));
            data.add(input);
        }
        List<Integer> result = new ArrayList<>();

        while (!data.isEmpty()) {
            Deque<Integer> currentDeque = new ArrayDeque<>(data.pop());
            int wave = currentDeque.pop();
            result.add(wave);
            if (currentDeque.isEmpty()) {
                continue;
            }
            while (wave >= currentDeque.peek()) {
                currentDeque.pop();
                if (currentDeque.isEmpty()) {
                    break;
                }
            }
            if (currentDeque.isEmpty()) {
                continue;
            }
            data.add(currentDeque);
        }

        System.out.println(result.size());
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }
}
/*

3
5 3 2 1 4 4 5 8 6
7 5 5 6 4 3
1 2 3

*/
