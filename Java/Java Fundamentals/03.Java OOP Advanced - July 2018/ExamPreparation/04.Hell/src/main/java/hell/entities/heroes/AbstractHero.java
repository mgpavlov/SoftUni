package hell.entities.heroes;

import hell.controllers.CommandControllerImpl;
import hell.entities.miscellaneous.HeroInventory;
import hell.interfaces.Hero;
import hell.interfaces.Inventory;
import hell.interfaces.Item;
import hell.interfaces.Recipe;

import java.util.Collection;
import java.util.List;

public abstract class AbstractHero implements Hero {
    private String name;
    private int strength;
    private int agility;
    private int intelligence;
    private int hitPoints;
    private int damage;
    private List<Item> items;
    private List<Recipe> recipes;
    private Inventory inventory;

    public AbstractHero(String name) {
        this.name = name;
        this.inventory = new HeroInventory();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public long getStrength() {
        return this.strength;
    }

    @Override
    public long getAgility() {
        return this.agility;
    }

    @Override
    public long getIntelligence() {
        return this.intelligence;
    }

    @Override
    public long getHitPoints() {
        return this.hitPoints;
    }

    @Override
    public long getDamage() {
        return this.damage;
    }

    @Override
    public Collection<Item> getItems() {
        return this.items;
    }

    @Override
    public void addItem(Item item) {
        this.items.add(item);
        this.inventory.addCommonItem(item);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        this.inventory.addRecipeItem(recipe);
    }
}
