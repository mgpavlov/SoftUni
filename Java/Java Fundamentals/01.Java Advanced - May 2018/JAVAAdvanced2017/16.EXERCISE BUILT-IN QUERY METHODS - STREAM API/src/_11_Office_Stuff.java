import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class _11_Office_Stuff {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, LinkedHashMap<String, Integer>> companies = new TreeMap<>();

        int companiesCount = Integer.valueOf(bf.readLine());

        for (int i = 0; i < companiesCount; i++) {
            String[] token = bf.readLine().split("\\s+-\\s+");
            String company = token[0].substring(1);
            int itemCount = Integer.valueOf(token[1]);
            String item = token[2].substring(0, token[2].length() - 1);

            companies.putIfAbsent(company, new LinkedHashMap<>());
            companies.get(company).putIfAbsent(item, 0);
            itemCount += companies.get(company).get(item);
            companies.get(company).put(item, itemCount);
        }

        companies.entrySet()
                .forEach(c -> {
                    System.out.printf("%s: ", c.getKey());
                    StringBuilder sb = new StringBuilder();
                    c.getValue().entrySet()
                            .forEach(i -> sb.append(i.getKey()).append("-").append(i.getValue()).append(", "));
                    System.out.println(sb.delete(sb.length() - 2, sb.length() - 1).toString());
                });
    }
}
