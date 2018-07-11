package _03_Inheritance.EXERCISES._01_Person;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        int age = Integer.valueOf(scan.nextLine());


        try {
            Child child = new Child(name, age);
            System.out.println(child.toString());
            String personClassName = Person.class.getSimpleName();
            String childClassName = Child.class.getSimpleName();

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}
