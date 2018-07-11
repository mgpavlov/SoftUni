package p04FragileBaseClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Animal {
    protected List<Food> foodEaten;
    public Animal (){
        foodEaten = new ArrayList<Food>();
    }

    public void eat(Food food){
        this.foodEaten.add(food);
    }
    public final void eatAll (Food[] foods){
        this.foodEaten.addAll(Arrays.asList(foods));
    }
}
