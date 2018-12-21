package p10Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input1 = reader.readLine();

        String name = input1.split("\\s+")[0] + " " + input1.split("\\s+")[1];
        String address = input1.split("\\s+")[2];

        String input2 = reader.readLine();
        String name2 = input2.split("\\s+")[0];
        String beerL = input2.split("\\s+")[1];

        String input3 = reader.readLine();
        int integer = Integer.parseInt(input3.split("\\s+")[0]);
        double aDouble = Double.parseDouble(input3.split("\\s+")[1]);

        Tuple tuple1 = new Tuple(name, address);
        Tuple tuple2 = new Tuple(name2, beerL);
        Tuple tuple3 = new Tuple(integer, aDouble);

        System.out.println(tuple1.toString());
        System.out.println(tuple2.toString());
        System.out.println(tuple3.toString());
    }
}
