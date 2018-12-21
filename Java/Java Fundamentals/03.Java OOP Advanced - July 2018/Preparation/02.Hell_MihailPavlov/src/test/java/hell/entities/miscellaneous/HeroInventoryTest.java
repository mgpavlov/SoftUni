package hell.entities.miscellaneous;

import hell.interfaces.Inventory;

import hell.interfaces.Item;
import hell.interfaces.Recipe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;


public class HeroInventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() throws Exception {
        this.inventory = new HeroInventory();

        Item commonItem1 = Mockito.mock(Item.class);
        Item commonItem2 = Mockito.mock(Item.class);
        Item commonItem3 = Mockito.mock(Item.class);
        Mockito.when(commonItem1.getName()).thenReturn("A");
        Mockito.when(commonItem2.getName()).thenReturn("B");
        Mockito.when(commonItem3.getName()).thenReturn("C");

        Mockito.when(commonItem1.getAgilityBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem2.getAgilityBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem3.getAgilityBonus()).thenReturn(Integer.MAX_VALUE);


        Mockito.when(commonItem1.getDamageBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem2.getDamageBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem3.getDamageBonus()).thenReturn(Integer.MAX_VALUE);

        Mockito.when(commonItem1.getHitPointsBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem2.getHitPointsBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem3.getHitPointsBonus()).thenReturn(Integer.MAX_VALUE);

        Mockito.when(commonItem1.getIntelligenceBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem2.getIntelligenceBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem3.getIntelligenceBonus()).thenReturn(Integer.MAX_VALUE);

        Mockito.when(commonItem1.getStrengthBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem2.getStrengthBonus()).thenReturn(Integer.MAX_VALUE);
        Mockito.when(commonItem3.getStrengthBonus()).thenReturn(Integer.MAX_VALUE);

        this.inventory.addCommonItem(commonItem1);
        this.inventory.addCommonItem(commonItem2);
        this.inventory.addCommonItem(commonItem3);

        Recipe recipe = Mockito.mock(Recipe.class);
        ArrayList<String> requiredItems = new ArrayList<>();
        requiredItems.add("A");
        requiredItems.add("B");
        requiredItems.add("D");
        Mockito.when(recipe.getName()).thenReturn("Recipe1");
        Mockito.when(recipe.getRequiredItems()).thenReturn(requiredItems);
        this.inventory.addRecipeItem(recipe);
    }

    @Test
    public void getTotalAgilityIntelligenceDamageHitPoimtsStrengthBonuses() {
        Assert.assertEquals(3L*Integer.MAX_VALUE, this.inventory.getTotalAgilityBonus());
        Assert.assertEquals(3L*Integer.MAX_VALUE, this.inventory.getTotalStrengthBonus());
        Assert.assertEquals(3L*Integer.MAX_VALUE, this.inventory.getTotalDamageBonus());
        Assert.assertEquals(3L*Integer.MAX_VALUE, this.inventory.getTotalHitPointsBonus());
        Assert.assertEquals(3L*Integer.MAX_VALUE, this.inventory.getTotalIntelligenceBonus());
    }

    @Test
    public void addRecipeItemAddCommonItem() throws NoSuchFieldException, IllegalAccessException {
        Field recipes = this.inventory.getClass().getDeclaredField("recipeItems");
        recipes.setAccessible(true);
        Map<String, Recipe> recipesMap = (Map<String, Recipe>) recipes.get(this.inventory);
        Assert.assertEquals(1, recipesMap.size());
    }
    @Test
    public void AddCommonItem() throws NoSuchFieldException, IllegalAccessException {
        Field commonItems = this.inventory.getClass().getDeclaredField("commonItems");
        commonItems.setAccessible(true);
        Map<String, Item> items = (Map<String, Item>) commonItems.get(this.inventory);

        Item item4 = Mockito.mock(Item.class);
        Mockito.when(item4.getName()).thenReturn("D");
        this.inventory.addCommonItem(item4);

        Assert.assertEquals(2, items.size());
    }
}
