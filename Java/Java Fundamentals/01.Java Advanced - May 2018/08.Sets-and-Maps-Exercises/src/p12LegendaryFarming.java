import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class p12LegendaryFarming {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> keyMaterials = new TreeMap<>();
        keyMaterials.put("shards", 0);
        keyMaterials.put("fragments", 0);
        keyMaterials.put("motes", 0);
        Map<String, Integer> junkMaterials = new TreeMap<>();

        while (true) {
            String[] input = reader.readLine().toLowerCase().split("\\s+");

            for (int i = 0; i < input.length; i += 2) {
                String material = input[i + 1];
                int quantity = Integer.parseInt(input[i]);
                if (material.equals("shards") ||
                        material.equals("fragments") ||
                        material.equals("motes")) {
                    keyMaterials.put(material, keyMaterials.get(material) + quantity);
                    if (keyMaterials.get(material) >= 250) {
                        if (material.equals("shards")) {
                            System.out.println("Shadowmourne obtained!");
                            keyMaterials.put("shards", keyMaterials.get("shards") - 250);
                            keyMaterials.entrySet().stream().sorted((a, b) -> {
                                return Integer.compare(b.getValue(), a.getValue());
                            }).forEach(x -> {
                                System.out.printf("%s: %d%n", x.getKey(), x.getValue());
                            });

                            junkMaterials.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
                            return;
                        } else if (material.equals("fragments")) {
                            System.out.println("Valanyr obtained!");
                            keyMaterials.put("fragments", keyMaterials.get("fragments") - 250);
                            keyMaterials.entrySet().stream().sorted((a, b) -> {
                                return Integer.compare(b.getValue(), a.getValue());
                            }).forEach(x -> {
                                System.out.printf("%s: %d%n", x.getKey(), x.getValue());
                            });

                            junkMaterials.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
                            return;
                        } else if (material.equals("motes")) {
                            System.out.println("Dragonwrath obtained!");
                            keyMaterials.put("motes", keyMaterials.get("motes") - 250);
                            keyMaterials.entrySet().stream().sorted((a, b) -> {
                                return Integer.compare(b.getValue(), a.getValue());
                            }).forEach(x -> {
                                System.out.printf("%s: %d%n", x.getKey(), x.getValue());
                            });

                            junkMaterials.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
                            return;
                        }
                    }
                } else {
                    junkMaterials.putIfAbsent(material, 0);
                    junkMaterials.put(material.toLowerCase(), junkMaterials.get(material) + quantity);
                }
            }
        }
    }
}
