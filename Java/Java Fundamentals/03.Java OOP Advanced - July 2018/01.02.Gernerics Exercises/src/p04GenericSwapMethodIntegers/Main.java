package p04GenericSwapMethodIntegers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        List <Integer> container = new ArrayList<>();
        while(input-- > 0){
            String command = reader.readLine();
            container.add(Integer.valueOf(command));
        }
        String[] tokens = reader.readLine().split("\\s+");
        int index1 = Integer.parseInt(tokens[0]);
        int index2 = Integer.parseInt(tokens[1]);

        Box box = new Box(container);
        box.swapElements(index1, index2);
        System.out.println(box.toString());
    }
}
