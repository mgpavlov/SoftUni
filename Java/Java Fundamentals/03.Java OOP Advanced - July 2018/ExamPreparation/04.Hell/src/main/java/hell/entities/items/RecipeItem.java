package hell.entities.items;

import hell.interfaces.Recipe;

import java.util.List;

public class RecipeItem extends AbstractItem implements Recipe {
    @Override
    public List<String> getRequiredItems() {
        return null;
    }
}
