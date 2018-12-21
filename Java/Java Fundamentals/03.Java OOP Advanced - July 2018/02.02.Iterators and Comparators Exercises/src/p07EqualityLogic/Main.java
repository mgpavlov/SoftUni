package p07EqualityLogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {

        Set<EqualityLogicPerson> treeSet = new TreeSet<>();
        Set<EqualityLogicPerson> hashSet = new HashSet<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            String info[] = reader.readLine().split("\\s+");
            String name = info[0];
            int age = Integer.parseInt(info[1]);

            treeSet.add(new EqualityLogicPerson(name, age));
            hashSet.add(new EqualityLogicPerson(name, age));
        }

        System.out.println(treeSet.size() + "\n" + hashSet.size());
    }
}
