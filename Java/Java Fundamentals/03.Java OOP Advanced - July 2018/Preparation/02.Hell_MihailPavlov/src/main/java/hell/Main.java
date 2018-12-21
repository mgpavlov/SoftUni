package hell;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommontItem;
import hell.entities.items.RecipeItem;
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Hero> heroes = new LinkedHashMap<>();
        try {
            String line = reader.readLine();
            while (true) {
                if ("Quit".equals(line)) {
                    break;
                }
                String[] lineTokens = line.split("\\s+");

                switch (lineTokens[0]) {
                    case "Hero":
                        String name = lineTokens[1];
                        String heroType = lineTokens[2];
                        Hero hero = null;
                        Inventory inventory = new HeroInventory();
                        switch (heroType) {
                            case "Barbarian":
                                hero = new Barbarian(name, inventory);
                                break;
                            case "Assassin":
                                hero = new Assassin(name, inventory);
                                break;
                            case "Wizard":
                                hero = new Wizard(name, inventory);
                                break;
                        }
                        if (hero != null) {
                            heroes.put(name, hero);
                            System.out.println(String.format("Created %s - %s", heroType, name));
                            break;
                        }
                        break;
                    case "Item":
                        String itemName = lineTokens[1];
                        String heroName = lineTokens[2];
                        int strength = Integer.parseInt(lineTokens[3]);
                        int agility = Integer.parseInt(lineTokens[4]);
                        int intelligence = Integer.parseInt(lineTokens[5]);
                        int hitPoints = Integer.parseInt(lineTokens[6]);
                        int damage = Integer.parseInt(lineTokens[7]);

                        Item item = new CommontItem(itemName, strength, agility, intelligence, hitPoints, damage);

                        if (heroes.containsKey(heroName)) {
                            hero = heroes.get(heroName);
                            hero.addItem(item);
                            System.out.println(String.format("Added item - %s to Hero - %s", itemName, heroName));
                            break;
                        }
                        break;
                    case "Recipe":
                        String recipeName = lineTokens[1];
                        heroName = lineTokens[2];
                        strength = Integer.parseInt(lineTokens[3]);
                        agility = Integer.parseInt(lineTokens[4]);
                        intelligence = Integer.parseInt(lineTokens[5]);
                        hitPoints = Integer.parseInt(lineTokens[6]);
                        damage = Integer.parseInt(lineTokens[7]);
                        String[] requiredItems = Arrays.stream(lineTokens).skip(8).toArray(String[]::new); //todo
                        List<String> requiredItemsList = Arrays.asList(requiredItems);

                        Recipe recipe = new RecipeItem(recipeName, strength, agility, intelligence, hitPoints, damage, requiredItemsList);

                        if (heroes.containsKey(heroName)) {
                            hero = heroes.get(heroName);
                            hero.addRecipe(recipe);
                            System.out.println(String.format("Added recipe - %s to Hero - %s", recipeName, heroName));
                            break;
                        }
                        break;
                    case "Inspect":
                        heroName = lineTokens[1];
                        System.out.println(heroes.get(heroName).toString());
                        break;
                }
                line = reader.readLine();
            }

            StringBuilder sb = new StringBuilder();
            final int[] num = {1};
            heroes.entrySet().stream().sorted((a, b) -> {
                long firstCriterionA = a.getValue().getStrength() + a.getValue().getAgility() + a.getValue().getIntelligence();
                long firstCriterionB = b.getValue().getStrength() + b.getValue().getAgility() + b.getValue().getIntelligence();

                long secondCriterionA = a.getValue().getHitPoints() + a.getValue().getDamage();
                long secondCriterionB = b.getValue().getHitPoints() + b.getValue().getDamage();

                if (Long.compare(firstCriterionB, firstCriterionA) == 0) {
                    return Long.compare(secondCriterionB, secondCriterionA);
                }
                return Long.compare(firstCriterionB, firstCriterionA);
            }).forEach(h -> {
                sb.append(String.format("%d. %s: %s", num[0], h.getValue().getClass().getSimpleName(), h.getValue().getName())).append(System.lineSeparator())
                        .append(String.format("###HitPoints: %d", h.getValue().getHitPoints())).append(System.lineSeparator())
                        .append(String.format("###Damage: %d", h.getValue().getDamage())).append(System.lineSeparator())
                        .append(String.format("###Strength: %d", h.getValue().getStrength())).append(System.lineSeparator())
                        .append(String.format("###Agility: %d", h.getValue().getAgility())).append(System.lineSeparator())
                        .append(String.format("###Intelligence: %d", h.getValue().getIntelligence())).append(System.lineSeparator())
                        .append("###Items: ");

                List<String> items = new ArrayList<>();
                for (Item item : h.getValue().getItems()) {
                    items.add(item.getName());
                }
                if (items.size() == 0){
                    sb.append("None").append(System.lineSeparator());
                }else {
                    sb.append(String.join(", ", items)).append(System.lineSeparator());
                }
                num[0]++;
            });

            System.out.println(sb.toString());
        }catch (Exception ignored){

        }
    }
}