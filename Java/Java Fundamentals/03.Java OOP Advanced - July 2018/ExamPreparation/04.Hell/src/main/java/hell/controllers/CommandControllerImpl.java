package hell.controllers;

import hell.annotations.ItemCollection;
import hell.interfaces.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CommandControllerImpl implements CommandController {

    private OutputWriter writer;
    private HeroFactory heroFactory;
    private ItemFactory itemFactory;
    private RecipeFactory recipeFactory;
    private Map<String, Hero> heroesByName;

    public CommandControllerImpl(HeroFactory heroFactory, RecipeFactory recipeFactory, ItemFactory itemFactory, OutputWriter writer) {
        this.heroFactory = heroFactory;
        this.recipeFactory = recipeFactory;
        this.itemFactory = itemFactory;
        this.writer = writer;
        this.heroesByName = new LinkedHashMap<>();
    }

    @Override
    public void createHero(String heroName, String heroType) throws InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Hero hero = this.heroFactory.create(heroName, heroType);
        heroesByName.put(heroName, hero);
        writer.writeLine(String.format("Created %s - %s", heroType, heroName));
    }

    @Override
    public void createCommonItem(String itemName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus) {
        Item item = this.itemFactory.create(itemName);
        Hero hero = heroesByName.get(heroName);
        hero.addItem(item);

        writer.writeLine(String.format("Added item - %s to Hero - %s", itemName, heroName));
    }

    @Override
    public void createRecipeItem(String recipeName, String heroName, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitpointsBonus, int damageBonus, String... requiredItems) {
        Recipe recipe = this.recipeFactory.create(recipeName);
        Hero hero = heroesByName.get(heroName);
        hero.addRecipe(recipe);
    }

    @Override
    public void inspectHero(String heroName) {

    }

    @Override
    public void quit() {

    }
}