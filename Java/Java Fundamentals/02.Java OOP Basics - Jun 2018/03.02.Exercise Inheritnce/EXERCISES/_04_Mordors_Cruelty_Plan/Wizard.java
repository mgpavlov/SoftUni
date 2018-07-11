package _03_Inheritance.EXERCISES._04_Mordors_Cruelty_Plan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wizard {

    private static final Map<String, Integer> FOODS = new HashMap<String, Integer>() {
        {
            put("cram", 2);
            put("lembas", 3);
            put("apple", 1);
            put("melon", 1);
            put("honeycake", 5);
            put("mushrooms", -10);
        }
    };

    private int happynessIndex;


    public void setHappinessIndex(List<String> foods) {
        foods.forEach(this::eatFood);
    }

    public String getMood() {
        if (this.happynessIndex < -5) {
            return "Angry";

        } else if (this.happynessIndex <= 0) {
            return "Sad";

        } else if (this.happynessIndex <= 15) {
            return "Happy";

        } else {
            return "JavaScript";
        }
    }

    private void eatFood(String food) {
        String foodlowerName = food.toLowerCase();

        if (FOODS.containsKey(foodlowerName)) {
            this.happynessIndex += FOODS.get(foodlowerName);
        }
        else {
            this.happynessIndex += -1;
        }
    }
    public int getHappinessIndex() {
        return this.happynessIndex;
    }

}
