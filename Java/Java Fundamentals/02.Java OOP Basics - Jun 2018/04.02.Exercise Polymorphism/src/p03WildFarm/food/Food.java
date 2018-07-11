package p03WildFarm.food;

public abstract class Food {
  private Integer quantity;
  private String foodType;
  
  public Food(Integer quantity, String foodType) {
    this.setQuantity(quantity);
    this.setFoodType(foodType);
  }
  
  private void setFoodType(String foodType) {
    this.foodType = foodType;
  }
  
  public String getFoodType() {
    return this.foodType;
  }
  
  private void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }
  
  public int getQuantity() {
    return this.quantity;
  }
}
