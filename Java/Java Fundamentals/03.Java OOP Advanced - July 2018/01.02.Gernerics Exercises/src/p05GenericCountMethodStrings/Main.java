package p05GenericCountMethodStrings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(reader.readLine());
        List <String> container = new ArrayList<>();
        while(input-- > 0){
            String command = reader.readLine();
            container.add(command);
        }
        String comparator = reader.readLine();


        Box box = new Box(container);
        System.out.println(box.compareElements(comparator));
    }
}
