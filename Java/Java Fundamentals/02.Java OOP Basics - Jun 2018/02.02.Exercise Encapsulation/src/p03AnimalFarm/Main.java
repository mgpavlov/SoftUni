package p03AnimalFarm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        int age = Integer.parseInt(reader.readLine());
        DecimalFormat df = new DecimalFormat("#.###########");
        try {
            Chicken chicken = new Chicken(name, age);
            System.out.printf("Chicken %s (age %d) can produce %s eggs per day.", name, age, df.format(chicken.productPerDay()));
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

    }
}
