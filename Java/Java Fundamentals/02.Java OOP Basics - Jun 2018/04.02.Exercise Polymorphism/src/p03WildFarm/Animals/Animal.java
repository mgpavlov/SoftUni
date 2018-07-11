package p03WildFarm.Animals;


import p03WildFarm.food.Food;

public abstract class Animal {
  private String animalName;
  private String animalType;
  private Double animalWeight;
  private Integer foodEaten;
  
  public Animal(String animalName, String animalType, Double animalWeight, Integer foodEaten){
    this.setAnimalName(animalName);
    this.setAnimalType(animalType);
    this.setAnimalWeight(animalWeight);
    this.setFoodEaten(foodEaten);
  }
  
  protected final String getAnimalName() {
    return this.animalName;
  }
  
  private void setAnimalName(String animalName) {
    this.animalName = animalName;
  }
  
  protected final String getAnimalType() {
    return this.animalType;
  }
  
  private void setAnimalType(String animalType) {
    this.animalType = animalType;
  }
  
  protected final Double getAnimalWeight() {
    return this.animalWeight;
  }
  
  private void setAnimalWeight(Double animalWeight) {
    this.animalWeight = animalWeight;
  }
  
  protected final Integer getFoodEaten() {
    return this.foodEaten;
  }
  
  protected void setFoodEaten(Integer foodEaten) {
    this.foodEaten = foodEaten;
  }
  
  public abstract void makeSound();
  public abstract void eat(Food food);
  
  @Override
  public abstract String toString();


}
