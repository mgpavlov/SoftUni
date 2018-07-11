import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.*;
import java.util.stream.Collectors;

public class _15_Legendary_Farming
{
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> keyMaterialsQuantity = new TreeMap<>();
        Map<String, Integer> junkMaterialsQuantity = new TreeMap<>();

        keyMaterialsQuantity.put("shards", 0);
        keyMaterialsQuantity.put("fragments", 0);
        keyMaterialsQuantity.put("motes", 0);

        while (true) {
            List<String> materials = new LinkedList<>();
            List<Integer> quantity = new LinkedList<>();

            readInputLine(bf, materials, quantity);

            for (int index = 0; index < materials.size(); index++) {
                String currentMaterialName = materials.get(index);
                int currentMaterialQuantity = quantity.get(index);

                if ("shards".equalsIgnoreCase(currentMaterialName)
                        || "fragments".equalsIgnoreCase(currentMaterialName)
                        || "motes".equalsIgnoreCase(currentMaterialName)) {
                    addCurrentKeyMaterialQuantity(keyMaterialsQuantity, currentMaterialName, currentMaterialQuantity);
                } else {
                    addCurrentJunkMaterialQuantity(junkMaterialsQuantity, currentMaterialName, currentMaterialQuantity);
                }

                boolean isLegendaryObtained = false;
                //Check if key materials and quantity is enough to obtain legendary
                switch (currentMaterialName) {
                    case "shards":
                        if (keyMaterialsQuantity.get(currentMaterialName) >= 250) {
                            System.out.println("Shadowmourne obtained!");

                            isLegendaryObtained = true;
                        }
                        break;
                    case "fragments":
                        if (keyMaterialsQuantity.get(currentMaterialName) >= 250) {
                            System.out.println("Valanyr obtained!");

                            isLegendaryObtained = true;
                        }
                        break;
                    case "motes":
                        if (keyMaterialsQuantity.get(currentMaterialName) >= 250) {
                            System.out.println("Dragonwrath obtained!");

                            isLegendaryObtained = true;
                        }
                        break;
                }

                if (isLegendaryObtained) {
                    int leftKeyMaterialQuantity = keyMaterialsQuantity.get(currentMaterialName) - 250;
                    keyMaterialsQuantity.put(currentMaterialName, leftKeyMaterialQuantity);
                    //print left material and quantity
                    printLeftMaterialsAndQuantity(keyMaterialsQuantity, junkMaterialsQuantity);
                    return;
                }
            }
        }
    }

    private static void printLeftMaterialsAndQuantity(Map<String, Integer> keyMaterialsQuantity
            , Map<String, Integer> junkMaterialsQuantity) {

        LinkedHashMap<String, Integer> orderedMaterialsByQuantity = keyMaterialsQuantity.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        for (Map.Entry<String, Integer> keyMaterial : orderedMaterialsByQuantity.entrySet()) {
            System.out.printf("%s: %d%n", keyMaterial.getKey(), keyMaterial.getValue());
        }

        for (Map.Entry<String, Integer> junkMaterial : junkMaterialsQuantity.entrySet()) {
            System.out.printf("%s: %d%n", junkMaterial.getKey(), junkMaterial.getValue());
        }
    }

    private static void addCurrentKeyMaterialQuantity(Map<String, Integer> keyMaterialsQuantity
            , String currentMaterialName, int currentMaterialQuantity) {

        int totalQuantity = keyMaterialsQuantity.get(currentMaterialName) + currentMaterialQuantity;
        keyMaterialsQuantity.put(currentMaterialName, totalQuantity);
    }

    private static void addCurrentJunkMaterialQuantity(Map<String, Integer> junkMaterialsQuantity
            , String currentMaterialName, int currentMaterialQuantity) {

        if (!junkMaterialsQuantity.containsKey(currentMaterialName)) {
            junkMaterialsQuantity.put(currentMaterialName, 0);
        }

        int totalQuantity = junkMaterialsQuantity.get(currentMaterialName) + currentMaterialQuantity;
        junkMaterialsQuantity.put(currentMaterialName, totalQuantity);
    }

    private static void readInputLine(BufferedReader bf, List<String> materials, List<Integer> quantity) throws IOException {
        String[] line = bf.readLine().toLowerCase().split("\\s+");

        Arrays.stream(line).filter(el -> {
            try {
                quantity.add(Integer.valueOf(el));
                return true;
            } catch (Exception e) {
                materials.add(el);
                return false;
            }
        }).map(Integer::valueOf).collect(Collectors.toList());
    }
}