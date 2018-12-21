package hell.entities.miscellaneous;

import hell.entities.items.CommontItem;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.*;

public class HeroInventory implements Inventory {

    @ItemCollection
    private Map<String, Item> commonItems;
    private Map<String, Recipe> recipeItems;


    public HeroInventory() {
        this.commonItems = new LinkedHashMap<>();
        this.recipeItems = new LinkedHashMap<>();
    }

    @Override
    public long getTotalStrengthBonus() {
        return this.commonItems.entrySet().stream().mapToLong((x) -> x.getValue().getStrengthBonus()).sum();
    }

    @Override
    public long getTotalAgilityBonus() {
        return this.commonItems.entrySet().stream().mapToLong((x) -> x.getValue().getAgilityBonus()).sum();
    }

    @Override
    public long getTotalIntelligenceBonus() {
        return this.commonItems.entrySet().stream().mapToLong((x) -> x.getValue().getIntelligenceBonus()).sum();
    }

    @Override
    public long getTotalHitPointsBonus() {
        return this.commonItems.entrySet().stream().mapToLong((x) -> x.getValue().getHitPointsBonus()).sum();
    }

    @Override
    public long getTotalDamageBonus() {
        return this.commonItems.entrySet().stream().mapToLong((x) -> x.getValue().getDamageBonus()).sum();
    }

    @Override
    public void addCommonItem(Item item) {
        this.commonItems.put(item.getName(), item);
        this.checkRecipes();
    }

    @Override
    public void addRecipeItem(Recipe recipe) {
        this.recipeItems.put(recipe.getName(), recipe);
        this.checkRecipes();
    }

    private void checkRecipes() {
        for (Recipe recipe : this.recipeItems.values()) {
            List<String> requiredItems = new ArrayList<>(recipe.getRequiredItems());

            for (String itemName : this.commonItems.keySet()) {
                if (requiredItems.contains(itemName)) {
                    requiredItems.remove(itemName);
                }
            }

            if (requiredItems.isEmpty()) {
                this.combineRecipe(recipe);
                //break;//todo
            }
        }
    }

    private void combineRecipe(Recipe recipe) {
        for (String requiredItem : recipe.getRequiredItems()) {
            this.commonItems.remove(requiredItem);
        }

        String name = recipe.getName();
        int strengthBonus = recipe.getStrengthBonus();
        int agilityBonus = recipe.getAgilityBonus();
        int intelligenceBonus = recipe.getIntelligenceBonus();
        int hitPointsBonus = recipe.getHitPointsBonus();
        int damageBonus = recipe.getDamageBonus();

        Item newItem = new CommontItem(name, strengthBonus,agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.commonItems.put(name, newItem);
        this.recipeItems.remove(name);
    }
}