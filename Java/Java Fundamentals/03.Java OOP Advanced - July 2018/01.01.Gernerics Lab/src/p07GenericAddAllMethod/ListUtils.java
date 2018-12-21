package p07GenericAddAllMethod;

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

    public static <T> List<Integer> getNullIndices(List<?> list)  {
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
             if (list.get(i) == null){
                 indices.add(i);
             }

        }
        return  indices;
    }

    public static <T> void flatten (List<? super T> destination, List<List<? extends T>> source){
        for (List list : source) {
            destination.addAll(list);
        }
    }
    public static <T> void addAll (List<? super T> destination, List<? extends T> source){
        for (T list : source) {
            destination.add(list);
        }
    }

}
