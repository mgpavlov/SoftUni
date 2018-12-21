package p06GenericFlatMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1,2,5,8,3,0);

        List<Double> doubles = new ArrayList<>();
        Collections.addAll(doubles, 1.2,2.7,5.0,8.4,3.9,0.7);

        List<List<? extends  Number>> jagged = new ArrayList<>();
        Collections.addAll(jagged, integers, doubles);
        List<Number> dest = new ArrayList<>();
        ListUtils.flatten(dest, jagged);

        System.out.println(dest);
    }
}
