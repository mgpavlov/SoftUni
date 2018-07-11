package p01ClassBox;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {
        this.length = length;
    }

    private void setWidth(double width) {
        this.width = width;
    }

    private void setHeight(double height) {
        this.height = height;
    }

    public double getSurfaceArea (){
        return 2*(this.height* this.width + this.height* this.length+ this.width* this.length);
    }
    public double getLateralSurfaceArea (){
        return 2*(this.height* this.width + this.height* this.length);
    }
    public double getVolume (){
        return this.height* this.width* this.length;
    }

}
