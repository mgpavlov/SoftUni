package p03WildFarm.Animals;

public abstract class Mammal extends Animal{
  private String livingRegion;
  
  public Mammal(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
    super(animalName, animalType, animalWeight, foodEaten);
    this.setLivingRegion(livingRegion);
  }
  
  private void setLivingRegion(String livingRegion) {
    this.livingRegion = livingRegion;
  }
  
  protected final String getLivingRegion() {
    return this.livingRegion;
  }
  
}
