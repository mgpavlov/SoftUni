import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class p203Earthquake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(reader.readLine());

        List<List<Integer>> data = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> input = Arrays.stream(reader.readLine().split("\\s+")).filter(x -> !x.isEmpty()).map(Integer::parseInt).collect(Collectors.toList());
            data.add(input);
        }
        List<Integer> result = new ArrayList<>();
        int seismicity;

        while (true) {
            boolean isDataEmpty = true;
            for (int i = 0; i < n; i++) {
                if (data.get(i).isEmpty()) {
                    continue;
                } else {
                    seismicity = data.get(i).get(0);
                    result.add(seismicity);
                    data.get(i).remove(0);

                    for (int j = 0; j < data.get(i).size(); j++) {
                        if (seismicity >= data.get(i).get(j)) {
                            data.get(i).remove(j);
                            j--;
                        } else {
                            break;
                        }
                    }
                    isDataEmpty = false;
                }
            }
            if (isDataEmpty) {
                break;
            }
        }

        System.out.println(result.size());
        StringBuilder print = new StringBuilder();
        for (Integer integer : result) {
            print.append(integer).append(" ");
        }
        System.out.println(print);
    }
}