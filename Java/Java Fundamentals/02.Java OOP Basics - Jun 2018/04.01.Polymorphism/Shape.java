package _04_Polymorphism.LAB._03_Shapes;

public abstract class Shape {
    private Double perimeter;
    private double area;

    public final double getPerimeter() {
        return this.perimeter;
    }

    protected void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public final double getArea() {
        return this.area;
    }

    protected void setArea(double area) {
        this.area = area;
    }

    protected abstract double calculatePerimeter();
    protected abstract double calculateArea();
}
