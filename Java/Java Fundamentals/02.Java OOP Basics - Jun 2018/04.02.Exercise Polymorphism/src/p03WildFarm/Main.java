package p03WildFarm;

import p03WildFarm.Animals.*;
import p03WildFarm.food.Food;
import p03WildFarm.food.Meat;
import p03WildFarm.food.Vegetable;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
  
  private static List<Animal> animals = new ArrayList<>();
  
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    
        while (true) {
          String[] animalTokens = reader.readLine().split(" ");
          
          if (animalTokens[0].equals("End")) {
            break;
          }
          
          String[] foodTokens = reader.readLine().split(" ");
          Food food = createFood(foodTokens);
  
          Animal animal = createAnimal(animalTokens, food);
            
            
            //try {
            //  animal.eat(food);
            //} catch (IllegalArgumentException iae) {
            //  System.out.println(iae.getMessage());
            //}
            
            if (animal != null) {
              animals.add(animal);
            }
            
        }
   
    for (Animal animal : animals) {
      System.out.print(animal);
    }
    
    
  }
  
  private static Food createFood(String[] foodTokens) throws IOException {
    
    String foodType = foodTokens[0];
    Integer quantity = Integer.valueOf(foodTokens[1]);
    Food food = null;
    
    switch (foodType) {
      case "Vegetable":
        food = new Vegetable(quantity, foodType);
        break;
      case "Meat":
        food = new Meat(quantity, foodType);
        break;
    }
    return food;
  }
  
  private static Animal createAnimal(String[] animalTokens, Food food) throws IOException {
    
    //  {AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion} [{CatBreed} = Only if its cat]
    String animalType = animalTokens[0];
    String animalName = animalTokens[1];
    Double animalWeight = Double.valueOf(animalTokens[2]);
    String animalLivingRegion = animalTokens[3];
    Animal animal = null;
    switch (animalType) {
      case "Cat":
        String breed = animalTokens[4];
        try {
          animal = new Cat(animalName, animalType, animalWeight, 0, animalLivingRegion, breed);
          animal.makeSound();
          animal.eat(food);
        } catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
        }
        break;
      case "Mouse":
        try {
          animal = new Mouse(animalName, animalType, animalWeight, 0, animalLivingRegion);
          animal.makeSound();
          animal.eat(food);
        }  catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
        }
        break;
      case "Tiger":
        try {
          animal = new Tiger(animalName, animalType, animalWeight, 0, animalLivingRegion);
          animal.makeSound();
          animal.eat(food);
        }  catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
        }
        break;
      case "Zebra":
        try {
          animal = new Zebra(animalName, animalType, animalWeight, 0, animalLivingRegion);
          animal.makeSound();
          animal.eat(food);
        }  catch (IllegalArgumentException iae) {
          System.out.println(iae.getMessage());
        }
        break;
    }
    return animal;
  }
}
