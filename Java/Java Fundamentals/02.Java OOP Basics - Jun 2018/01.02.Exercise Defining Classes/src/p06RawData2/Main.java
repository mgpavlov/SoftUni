package p06RawData2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Car> cars = new LinkedList<>();
        Integer n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scan.nextLine().split("\\s+");
            String model = tokens[0];
            int engineSpeed = Integer.parseInt(tokens[1]);
            int enginePower = Integer.parseInt(tokens[2]);
            int cargoWeight = Integer.parseInt(tokens[3]);
            String cargoType = tokens[4];
            double tire1Pressure = Double.parseDouble(tokens[5]);
            int tire1Age = Integer.parseInt(tokens[6]);
            double tire2Pressure = Double.parseDouble(tokens[7]);
            int tire2Age = Integer.parseInt(tokens[8]);
            double tire3Pressure = Double.parseDouble(tokens[9]);
            int tire3Age = Integer.parseInt(tokens[10]);
            double tire4Pressure = Double.parseDouble(tokens[11]);
            int tire4Age = Integer.parseInt(tokens[12]);


            Engine engine = new Engine(engineSpeed, enginePower);
            Cargo cargo = new Cargo(cargoWeight, cargoType);
            Tyre tyre1 = new Tyre(tire1Pressure, tire1Age);
            Tyre tyre2 = new Tyre(tire2Pressure, tire2Age);
            Tyre tyre3 = new Tyre(tire3Pressure, tire3Age);
            Tyre tyre4 = new Tyre(tire4Pressure, tire4Age);
            Tyre[] tyres = {tyre1, tyre2, tyre3, tyre4};
            Car car = new Car(model, engine, cargo, tyres);
            cars.add(car);

        }



        String command = scan.nextLine();

        if (command.equals("fragile")) {
            cars.stream().filter(c -> c.getCargo().getType().equals("fragile"))
                    .filter(Car::checkTyresPressure).forEach( c -> System.out.println(c.getModel()));
        } else if (command.equals("flamable")) {
            cars.stream().filter(c -> c.getCargo().getType().equals("flamable"))
                    .filter(c -> c.getEngine().getPower() > 250).forEach( c -> System.out.println(c.getModel()));
        }


    }
}
