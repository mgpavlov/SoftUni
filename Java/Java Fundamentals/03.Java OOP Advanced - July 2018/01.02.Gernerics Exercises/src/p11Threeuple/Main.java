package p11Threeuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input1 = reader.readLine();

        String name = input1.split("\\s+")[0] + " " + input1.split("\\s+")[1];
        String address = input1.split("\\s+")[2];
        String town = input1.split("\\s+")[3];

        String input2 = reader.readLine();
        String name2 = input2.split("\\s+")[0];
        String beerL = input2.split("\\s+")[1];
        boolean drunkOrNot = input2.split("\\s+")[2].equals("drunk")? true: false;

        String input3 = reader.readLine();
        String name3 = input3.split("\\s+")[0];
        double aDouble = Double.parseDouble(input3.split("\\s+")[1]);
        String bankName = input3.split("\\s+")[2];

        Threeuple tuple1 = new Threeuple(name, address, town);
        Threeuple tuple2 = new Threeuple(name2, beerL, drunkOrNot);
        Threeuple tuple3 = new Threeuple(name3, aDouble, bankName);

        System.out.println(tuple1.toString());
        System.out.println(tuple2.toString());
        System.out.println(tuple3.toString());
    }
}
