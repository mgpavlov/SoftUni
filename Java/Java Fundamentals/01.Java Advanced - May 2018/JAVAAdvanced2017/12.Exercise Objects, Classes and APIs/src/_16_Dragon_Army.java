import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 20/100
import java.util.*;

public class _16_Dragon_Army {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, TreeMap<String, int[]>> allDragons = new LinkedHashMap<>();
        int dragonsCount = Integer.valueOf(bf.readLine());

        enterDragonsAndStats(bf, allDragons, dragonsCount);

        List<String> averageStatsByType = new ArrayList<>();
        calcAverageStatsByType(allDragons, averageStatsByType);

        int index = 0;
        for (Map.Entry<String, TreeMap<String, int[]>> dragons : allDragons.entrySet()) {
            String dragonsType = dragons.getKey();
            TreeMap<String, int[]> allDragonsByCurrentType = dragons.getValue();

            System.out.println(averageStatsByType.get(index));
            for (Map.Entry<String, int[]> dragon : allDragonsByCurrentType.entrySet()) {
                String name = dragon.getKey();
                int damage = dragon.getValue()[0];
                int health = dragon.getValue()[1];
                int armor = dragon.getValue()[2];
                System.out.printf("-%s -> damage: %d, health: %d, armor: %d%n", name, damage, health, armor);
            }

            index++;
        }
    }

    private static void calcAverageStatsByType(Map<String, TreeMap<String, int[]>> allDragons, List<String> averageStatsByType) {
        for (Map.Entry<String, TreeMap<String, int[]>> dragonsByType : allDragons.entrySet()) {
            String dragonsType = dragonsByType.getKey();
            TreeMap<String, int[]> allDragonsByCurrentType = dragonsByType.getValue();

            double averageDamage = 0.0;
            double averageHealth = 0.0;
            double averageArmor = 0.0;

            for (int[] stats : allDragonsByCurrentType.values()) {
                int currentDamage = stats[0];
                int currentHealth = stats[1];
                int currentArmor = stats[2];

                averageDamage += currentDamage;
                averageHealth += currentHealth;
                averageArmor += currentArmor;
            }

            averageDamage /= allDragonsByCurrentType.size();
            averageHealth /= allDragonsByCurrentType.size();
            averageArmor /= allDragonsByCurrentType.size();

            StringBuilder sb = new StringBuilder();
            sb.append(dragonsType + "::(");
            sb.append(String.format("%.2f", averageDamage) + "/");
            sb.append(String.format("%.2f", averageHealth) + "/");
            sb.append(String.format("%.2f", averageArmor) + ")");

            averageStatsByType.add(sb.toString());
        }
    }

    private static void enterDragonsAndStats(BufferedReader bf, Map<String, TreeMap<String, int[]>> allDragons
            , int dragonsCount) throws IOException {

        //damage 45, health 250, and armor 10
        int defaultDamage = 45;
        int defaultHealth = 250;
        int defaultArmor = 10;

        //{type} {name} {damage} {health} {armor}
        for (int index = 0; index < dragonsCount; index++) {
            String[] line = bf.readLine().split("\\s+");
            String dragonType = line[0];
            String dragonName = line[1];
            int damage = "null".equalsIgnoreCase(line[2]) ? defaultDamage : Integer.valueOf(line[2]);
            int health = "null".equalsIgnoreCase(line[3]) ? defaultHealth : Integer.valueOf(line[3]);
            int armor = "null".equalsIgnoreCase(line[4]) ? defaultArmor : Integer.valueOf(line[4]);

            if (!allDragons.containsKey(dragonType)) {
                TreeMap<String, int[]> currentDragon = new TreeMap<>();
                allDragons.put(dragonType, currentDragon);
            }

            TreeMap<String, int[]> dragonsByType = allDragons.get(dragonType);
            if (!dragonsByType.containsKey(dragonName)) {
                int[] currentStats = new int[3];
                dragonsByType.put(dragonName, currentStats);
            }

            int[] stats = {damage, health, armor};
            dragonsByType.put(dragonName, stats);
            allDragons.put(dragonType, dragonsByType);
        }
    }
}
