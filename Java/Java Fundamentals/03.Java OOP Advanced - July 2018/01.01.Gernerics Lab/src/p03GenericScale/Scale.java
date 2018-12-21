package p03GenericScale;

import java.util.Comparator;

public class Scale <T extends Comparable<T>> {
    private T left;
    private T right;

    public Scale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getHeavier(){
        int comparator = this.left.compareTo(this.right);
        if (comparator <0){
            return this.right;
        }else if (comparator > 0){
            return this.left;
        }
        return null;
    }
}
