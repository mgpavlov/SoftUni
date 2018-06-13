import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class p11LogsAggregator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        Map<String, Map<String, Long>> userLogs = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("\\s+");

            String ip = input[0];
            String name = input[1];
            long duration = Integer.parseInt(input[2]);

            userLogs.putIfAbsent(name, new TreeMap<>());
            userLogs.get(name).putIfAbsent(ip, 0L);
            userLogs.get(name).put(ip, userLogs.get(name).get(ip) + duration);
        }
        for (Map.Entry<String, Map<String, Long>> userInfo : userLogs.entrySet()) {
            System.out.printf("%s: %d ", userInfo.getKey(), userInfo.getValue().entrySet().stream().mapToLong(x->x.getValue()).sum());
            System.out.println(userInfo.getValue().keySet());
        }
    }
}
