package p05NullFinder;

import java.util.ArrayList;
import java.util.List;

public class ListUtils<T extends Comparable<T>> {
    public static <T extends Comparable<T>> T getMin(List<T> list){
        if (list.isEmpty()){
            throw new IllegalArgumentException("List should be not empty");
        }
        if (list.contains(null)){
            return null;
        }
        list.sort(Comparable::compareTo);
        return list.get(0);
    }

    public static <T extends Comparable<T>> T getMax(List<T> list){
        if (list.isEmpty()){
            throw new IllegalArgumentException("List should be not empty");
        }
        if (list.contains(null)){
            return null;
        }
        list.sort(Comparable::compareTo);
        return list.get(list.size()-1);
    }

    public static <T> List<Integer> getNullIndices(List<T> list)  {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
             if (list.get(i) == null){
                 indices.add(i);
             }

        }
        return  indices;
    }
}
