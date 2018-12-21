package p06GenericCountMethodDouble;

import java.util.List;

public class Box <T extends Comparable<T>>{
    private List<T> container;

    public Box(List<T> container) {
        this.container = container;
    }

    public int compareElements (T comparator){
        int elementsCount = 0;
        for (T t : this.container) {
            if (t.compareTo(comparator) > 0){
                elementsCount++;
            }
        }
        return elementsCount;
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
