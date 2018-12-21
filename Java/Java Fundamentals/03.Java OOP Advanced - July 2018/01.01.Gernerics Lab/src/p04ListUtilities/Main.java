package p04ListUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1,2,4,5,8,9,3,0,24);
        int maxInteger = ListUtils.getMax(integers);

        System.out.println(maxInteger);


        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, "a", "b", "c", "z", "l");
        String minString = ListUtils.getMin(stringList);

        System.out.println(minString);
    }
}
