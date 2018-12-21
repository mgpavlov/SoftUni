package p05NullFinder;

import p05NullFinder.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers, 1,2,null,5,8,null,3,0,null);

        List<String> stringList = new ArrayList<>();
        Collections.addAll(stringList, "a", null, "c", null, "l");

        List<Integer> nullList = ListUtils.getNullIndices(integers);
        List<Integer> nullListString = ListUtils.getNullIndices(stringList);

        System.out.println(nullList);
        System.out.println(nullListString);
    }
}
