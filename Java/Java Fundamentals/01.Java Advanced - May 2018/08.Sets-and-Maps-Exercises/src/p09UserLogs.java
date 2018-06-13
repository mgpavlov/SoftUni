import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class p09UserLogs {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "IP=(?<ip>.+?)\\s+.+?user=(?<username>.+)";
        Pattern pattern = Pattern.compile(regex);

        Map<String, Map<String, Integer>> data = new TreeMap<>();

        while (true) {
            String input = reader.readLine();
            if ("end".equals(input)) {
                break;
            }

            Matcher matcher = pattern.matcher(input);
            String username = "";
            String ip = "";
            if (matcher.find()) {
                username = matcher.group("username");
                ip = matcher.group("ip");
            }

            if (!data.containsKey(username)) {
                data.put(username, new LinkedHashMap<>());
            }
            if (!data.get(username).containsKey(ip)) {
                data.get(username).put(ip, 0);
            }
            data.get(username).put(ip, data.get(username).get(ip) + 1);
        }

        for (Map.Entry<String, Map<String, Integer>> user : data.entrySet()) {
            System.out.println(user.getKey() + ":");
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String, Integer> ip : user.getValue().entrySet()) {
                String temp = ip.getKey() + " => " + ip.getValue() + ", ";
                result.append(temp);
            }
            System.out.println(result.substring(0, result.length() - 2) + ".");
        }
    }
}
