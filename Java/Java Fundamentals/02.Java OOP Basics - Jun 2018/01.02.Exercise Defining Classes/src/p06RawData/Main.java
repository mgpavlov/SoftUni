package p06RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Car> cars = new ArrayList<>();
        while (n-->0) {
            String[] input = reader.readLine().split("\\s+");
            String model =input[0];
            int speed = Integer.parseInt(input[1]);
            int power = Integer.parseInt(input[2]);
            int weight = Integer.parseInt(input[3]);
            String type =input[4];
            double t1Pressure = Double.parseDouble(input[5]);
            int t1Age = Integer.parseInt(input[6]);
            double t2Pressure = Double.parseDouble(input[7]);
            int t2Age = Integer.parseInt(input[8]);
            double t3Pressure = Double.parseDouble(input[9]);
            int t3Age = Integer.parseInt(input[10]);
            double t4Pressure = Double.parseDouble(input[11]);
            int t4Age = Integer.parseInt(input[12]);

            Engine engine = new Engine(speed, power);
            Cargo cargo = new Cargo(weight, type);
            Tyre tyre1 = new Tyre(t1Pressure, t1Age);
            Tyre tyre2 = new Tyre(t2Pressure, t2Age);
            Tyre tyre3 = new Tyre(t3Pressure, t3Age);
            Tyre tyre4 = new Tyre(t4Pressure, t4Age);

            List<Tyre> tyres = new ArrayList<>(){{
                add(tyre1);
                add(tyre2);
                add(tyre3);
                add(tyre4);
            }};

            Car car = new Car(model, engine, cargo, tyres);
            cars.add(car);
        }

        String command = reader.readLine();
        if ("fragile".equals(command)){

            cars.stream()
                    .filter(c->c.getCargo().getType().equals("fragile"))
                    .forEach(c->{
                        c.getTyres().stream().filter(t->t.getPressure()<1).limit(1).forEach(t->{
                                System.out.println(c.toString());
                        });
                    });
        }else {
            cars.stream()
                    .filter(c->c.getCargo().getType().equals("flamable"))
                    .filter(c->c.getEngine().getPower() > 250)
                    .forEach(car -> {
                        System.out.println(car.toString());
                    });
        }
    }
}
