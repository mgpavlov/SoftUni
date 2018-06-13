import java.io.BufferedReader; // 100/100
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

public class p204GUnit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String regex = "^[A-Z][a-zA-Z0-9]+$";
        Map<String, Map<String, TreeSet<String>>> unitTests = new TreeMap<String, Map<String, TreeSet<String>>>();

        while (true) {
            String input = reader.readLine();
            if (input.equals("It's testing time!")) {
                break;
            }
            String[] tokens = input.split("\\s\\|\\s");
            if (tokens.length != 3) {
                continue;
            }
            String className = tokens[0];
            String method = tokens[1];
            String test = tokens[2];

            /*if (!Pattern.matches(regex, className) || !Pattern.matches(regex, method) || !Pattern.matches(regex, test)) {
                continue;
            }*/
            if (!className.matches(regex) || !method.matches(regex) || !test.matches(regex)) {
                continue;
            }
            unitTests.putIfAbsent(className, new TreeMap<>());
            unitTests.get(className).putIfAbsent(method, new TreeSet<>());
            unitTests.get(className).get(method).add(test);
        }
        StringBuilder result = new StringBuilder();
        unitTests.entrySet().stream().sorted((a, b) -> {
            if (Integer.compare(b.getValue().entrySet().stream().mapToInt(c -> c.getValue().size()).sum(), a.getValue().entrySet().stream().mapToInt(c -> c.getValue().size()).sum()) == 0) {
                return Integer.compare(a.getValue().keySet().size(), b.getValue().keySet().size());
            }
            return Integer.compare(b.getValue().entrySet().stream().mapToInt(c -> c.getValue().size()).sum(), a.getValue().entrySet().stream().mapToInt(c -> c.getValue().size()).sum());

        }).forEach(a -> {
            result.append(a.getKey()).append(":\n");
            a.getValue().entrySet().stream().sorted((g, z) -> {
                return Integer.compare(z.getValue().size(), g.getValue().size());
            }).forEach(s -> {
                result.append("##").append(s.getKey()).append("\n");
                s.getValue().stream().sorted((k, o) -> Integer.compare(k.length(), o.length())).forEach(e->{
                    result.append("####").append(e).append("\n");
                });
            });
        });
        System.out.println(result);
    }
}

