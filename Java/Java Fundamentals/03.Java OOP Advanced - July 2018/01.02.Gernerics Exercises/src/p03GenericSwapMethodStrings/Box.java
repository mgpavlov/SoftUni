package p03GenericSwapMethodStrings;

import java.util.ArrayList;
import java.util.List;

public class Box <T>{
    private List<T> container;

    public Box(List<T> container) {
        this.container = container;
    }

    public void swapElements (int index1, int index2){
        T temp = this.container.get(index1);
        this.container.set(index1, this.container.get(index2));
        this.container.set(index2, temp);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T data : this.container) {
            sb.append(String.format("%s: %s", data.getClass(), data).replaceAll("class ", "")).append(System.lineSeparator());
        }
       return sb.toString().trim();
    }
}
