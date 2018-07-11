package _03_Inheritance.EXERCISES._06_Animals;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            String animalKind = in.nextLine();

            if ("Beast!".equals(animalKind)){
                break;
            }

            String[] animalArgs = in.nextLine().split("\\s+");
            if (animalArgs.length >= 2) {
                String name = animalArgs[0];
                int age = Integer.parseInt(animalArgs[1]);

                Animal animal = null;
                try {
                    switch (animalKind) {
                        case "Animal":
                            if (animalArgs.length == 3) {
                                String gender = animalArgs[2];
                                animal = new Animal(name, age, gender);
                            }
                            break;
                        case "Dog":
                            if (animalArgs.length == 3) {
                                String gender = animalArgs[2];
                                animal = new Dog(name, age, gender);
                            }
                            break;
                        case "Cat":
                            if (animalArgs.length == 3) {
                                String gender = animalArgs[2];
                                animal = new Cat(name, age, gender);
                            }
                            break;
                        case "Kitten":
                            animal = new Kitten(name, age, "Female");
                            break;
                        case "Tomcat":
                            animal = new Tomcat(name, age, "Male");
                            break;
                        case "Frog":
                            if (animalArgs.length == 3) {
                                String gender = animalArgs[2];
                                animal = new Frog(name, age, gender);
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid input!");
                    }

                    if (animal != null) {
                        System.out.println(animalKind);
                        System.out.println(animal);
                        animal.produceSound();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}