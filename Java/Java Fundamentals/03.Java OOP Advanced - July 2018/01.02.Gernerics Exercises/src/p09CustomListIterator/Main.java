package p09CustomListIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Box box = new Box();
        while (true){
            String input = reader.readLine();
            if (input.equals("END")){
                break;
            }
            String[] tokens = input.split("\\s+");
            switch (tokens[0]){
                case "Add":
                    box.add(tokens[1]);
                    break;
                case "Remove":
                    box.remove(Integer.parseInt(tokens[1]));
                    break;
                case "Max":
                    System.out.println(box.getMax());
                    break;
                case "Min":
                    System.out.println(box.getMin());
                    break;
                case "Greater":
                    System.out.println(box.countGreaterThan(tokens[1]));
                    break;
                case "Swap":
                    box.swap(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    break;
                case "Contains":
                    System.out.println(box.contains(tokens[1]));
                    break;
                case "Print":
                    System.out.println(box.toString());
                    break;
                case "Sort":
                    box.sort();
                    break;
            }
        }
    }
}
