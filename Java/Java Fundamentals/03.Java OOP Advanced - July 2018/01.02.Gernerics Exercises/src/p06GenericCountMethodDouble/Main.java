package p06GenericCountMethodDouble;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        List <Double> container = new ArrayList<>();
        while(input-- > 0){
            double command = Double.parseDouble(reader.readLine());
            container.add(command);
        }
        double comparator = Double.parseDouble(reader.readLine());


        Box box = new Box(container);
        System.out.println(box.compareElements(comparator));
    }
}
