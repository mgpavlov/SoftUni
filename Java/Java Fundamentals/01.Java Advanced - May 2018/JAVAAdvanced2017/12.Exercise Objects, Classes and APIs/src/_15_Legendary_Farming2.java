import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap; // 100/100
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class _15_Legendary_Farming2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> junks = new TreeMap<>();
        Map<String, Integer> items = new HashMap<>();
        boolean foundDragonWrath = false;
        boolean foundValanyr = false;
        boolean foundShadowmourne = false;

        items.put("motes",0);
        items.put("shards",0);
        items.put("fragments",0);

        while (true) {
            String[] input = reader.readLine().split(" ");
            for (int i = 0; i < input.length; i += 2) {
                Integer quantity = Integer.parseInt(input[i]);
                String type = input[i + 1].toLowerCase();

                if ("motes".equals(type) || "shards".equals(type) || "fragments".equals(type)) {
                    Integer collected = items.get(type);
                    items.put(type, collected + quantity);
                    if (items.get(type) >= 250) {
                        items.put(type, items.get(type) - 250);
                        switch (type) {
                            case "motes":
                                foundDragonWrath = true;
                                break;
                            case "shards":
                                foundShadowmourne = true;
                                break;
                            default:
                                foundValanyr = true;
                                break;
                        }
                        break;
                    }

                } else {
                    if (!junks.containsKey(type)) {
                        junks.put(type, quantity);
                    } else {
                        Integer possessed = junks.get(type);
                        junks.put(type, possessed + quantity);
                    }
                }
            }

            if (foundDragonWrath || foundShadowmourne || foundValanyr) {
                break;
            }
        }
        if (foundDragonWrath) {
            System.out.println("Dragonwrath obtained!");
        }
        if (foundShadowmourne) {
            System.out.println("Shadowmourne obtained!");
        }
        if (foundValanyr) {
            System.out.println("Valanyr obtained!");
        }

        items = items.entrySet().stream().sorted(
                (a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            if (result == 0) {
                result = a.getKey().compareTo(b.getKey());
            }
            return result;
        })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Integer> entry : junks.entrySet()) {
            System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());
        }
    }
}
