package p07GenericAddAllMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1,2,5,8,3,0);

        List<Double> doubles = new ArrayList<>();
        Collections.addAll(doubles, 1.2,2.7,5.0,8.4,3.9,0.7);

        List<Number> dest = new ArrayList<>();


        ListUtils.addAll(dest, integers);
        System.out.println(dest);
        ListUtils.addAll(dest, doubles);
        System.out.println(dest);
    }
}
