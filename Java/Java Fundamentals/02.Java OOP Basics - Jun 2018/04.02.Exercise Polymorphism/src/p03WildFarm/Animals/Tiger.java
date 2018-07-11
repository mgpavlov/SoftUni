package p03WildFarm.Animals;


import p03WildFarm.food.Food;

import java.text.DecimalFormat;

public class Tiger extends Feline {
  
  public Tiger(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
    super(animalName, animalType, animalWeight, foodEaten, livingRegion);
  }
  
  @Override
  public void makeSound() {
    System.out.println("ROAAR!!!");
  }
  
  @Override
  public void eat(Food food) {
    if (!"Meat".equals(food.getFoodType())) {
      throw new IllegalArgumentException("Tigers are not eating that type of food!");
    } else {
      super.setFoodEaten(food.getQuantity());
    }
  }
  
  @Override
  public String toString() {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    
    StringBuilder stringBuilder = new StringBuilder()
            .append(this.getAnimalType())
            .append("[")
            .append(this.getAnimalName())
            .append(", ")
            .append(String.format("%s", decimalFormat.format(this.getAnimalWeight())))
            .append(", ")
            .append(this.getLivingRegion())
            .append(", ")
            .append(this.getFoodEaten())
            .append("]")
            .append(System.lineSeparator());
    
    return stringBuilder.toString();
  }
}
