package p03WildFarm.Animals;

import polymorphysm.exercises.wild_farm.food.Food;

import java.text.DecimalFormat;

public class Mouse extends Mammal{
  
  public Mouse(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
    super(animalName, animalType, animalWeight, foodEaten, livingRegion);
  }
  
  @Override
  public void makeSound() {
    System.out.println("SQUEEEAAAK!");
  }
  
  @Override
  public void eat(Food food) {
    if (!"Vegetable".equals(food.getFoodType())) {
      throw new IllegalArgumentException("Mice are not eating that type of food!");
    } else {
      super.setFoodEaten(food.getQuantity());
    }
  }
  
  @Override
  public String toString() {
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    //{AnimalType} [{AnimalName}, {CatBreed}, {AnimalWeight}, {AnimalLivingRegion}, {FoodEaten}]
    
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
