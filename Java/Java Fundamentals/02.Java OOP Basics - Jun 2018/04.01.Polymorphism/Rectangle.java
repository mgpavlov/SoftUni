package _04_Polymorphism.LAB._03_Shapes;
public class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        super();
        this.setHeight(height);
        this.setWidth(width);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public double getWidth() {
        return this.width;
    }

    @Override
    protected double calculatePerimeter() {
        return 2 * this.getHeight() * this.getWidth();
    }

    @Override
    protected double calculateArea() {
        return this.getHeight() * this.getWidth();
    }


}
