package _04_Polymorphism.LAB._03_Shapes;

public class Main {
    public static void main(String[] args) {

        Shape rect = new Rectangle(2, 5);
        Shape circle = new Circle(2.44);

        System.out.println(rect.calculateArea());
        System.out.println(rect.calculatePerimeter());
        System.out.println("-----------------------");
        System.out.println(circle.calculateArea());
        System.out.println(circle.calculatePerimeter());
    }
}
