package p03WildFarm.Animals;

import polymorphysm.exercises.wild_farm.food.Food;

import java.text.DecimalFormat;

public class Cat extends Feline{
  private String breed;
  
  public Cat(String animalName, String animalType
          , Double animalWeight, Integer foodEaten, String livingRegion, String breed) {
    super(animalName, animalType, animalWeight, foodEaten, livingRegion);
    this.setBreed(breed);
  }
  
  private void setBreed(String breed) {
    this.breed = breed;
  }
  
  protected final String getBreed() {
    return this.breed;
  }
  
  @Override
  public void makeSound() {
    System.out.println("Meowwww");
  }
  
  @Override
  public void eat(Food food) {
    super.setFoodEaten(food.getQuantity());
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
            .append(this.getBreed())
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
