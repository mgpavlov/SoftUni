package p03Shape;

public class Circle extends Shape{
    private  double r;

    public Circle(double r) {
        this.setR(r);
    }

    public double getR() {
        return this.r;
    }

    private void setR(double r) {
        this.r = r;
    }

    @Override
    double calculatePerimeter() {
        return 2*Math.PI*r;
    }

    @Override
    double calculateArea() {
        return Math.PI*r*r;
    }


}
