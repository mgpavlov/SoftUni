package p04ListUtilities;

import java.util.Comparator;
import java.util.List;

public class ListUtils <T extends Comparable<T>> {
    public static <T extends Comparable<T>> T getMin(List<T> list){
        if (list.isEmpty()){
            throw new IllegalArgumentException("List should be not empty");
        }
        list.sort(Comparable::compareTo);
        return list.get(0);
    }

    public static <T extends Comparable<T>> T getMax(List<T> list){
        list.sort(Comparable::compareTo);
        return list.get(list.size()-1);
    }
}
