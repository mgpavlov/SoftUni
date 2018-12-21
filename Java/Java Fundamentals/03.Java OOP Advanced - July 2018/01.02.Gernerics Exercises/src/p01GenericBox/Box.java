package p01GenericBox;

public class Box <T> {
    private T data;

    public Box(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.data.getClass(), data).replaceAll("class ", "");
    }
}
