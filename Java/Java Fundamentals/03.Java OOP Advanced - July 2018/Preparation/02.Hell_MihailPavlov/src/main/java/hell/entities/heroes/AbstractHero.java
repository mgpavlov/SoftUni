package hell.entities.heroes;

import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class AbstractHero implements Hero {
    private String name;

    private long strength;
    private long agility;
    private long intelligence;
    private long hitPoints;
    private long damage;
    private Inventory inventory;

    protected AbstractHero(String name, long strength, long agility, long intelligence, long hitPoints, long damage, Inventory inventory) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
        this.hitPoints = hitPoints;
        this.damage = damage;
        this.inventory = inventory;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength + this.inventory.getTotalStrengthBonus();
    }

    @Override
    public long getAgility() {
        return this.agility + this.inventory.getTotalAgilityBonus();
    }

    @Override
    public long getIntelligence() {
        return this.intelligence + this.inventory.getTotalIntelligenceBonus();
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints + this.inventory.getTotalHitPointsBonus();
    }

    @Override
    public long getDamage() {
        return this.damage + this.inventory.getTotalDamageBonus();
    }

    @Override
    public Collection<Item> getItems() {

        try {
            Field commonItems = HeroInventory.class.getDeclaredField("commonItems");
            commonItems.setAccessible(true);
            Map<String, Item> items = ((Map<String, Item>) commonItems.get(this.inventory));
            return items.values();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addItem(Item item) {
        this.inventory.addCommonItem(item);

    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.addRecipeItem(recipe);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Hero: %s, Class: %s", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator())
                .append(String.format("HitPoints: %d, Damage: %d", this.getHitPoints(), this.getDamage()))
                .append(System.lineSeparator())
                .append(String.format("Strength: %d", this.getStrength())).append(System.lineSeparator())
                .append(String.format("Agility: %d", this.getAgility())).append(System.lineSeparator())
                .append(String.format("Intelligence: %d", this.getIntelligence())).append(System.lineSeparator())
                .append("Items:");
        if (this.getItems().size() == 0){
            sb.append(" None");
        }else {
            for (Item item : this.getItems()) {
                sb.append(System.lineSeparator()).append(item.toString());
            }
        }
        return sb.toString().trim();
    }
}
