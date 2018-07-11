package p01Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] carInfo = reader.readLine().split("\\s+");
        double carFuelQuantity = Double.parseDouble(carInfo[1]);
        double carLitersPerKm = Double.parseDouble(carInfo[2]);
        Vehicles car = new Car(carFuelQuantity, carLitersPerKm);

        String[] truckInfo = reader.readLine().split("\\s+");
        double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
        double truckLitersPerKm = Double.parseDouble(truckInfo[2]);
        Vehicles truck = new Truck(truckFuelQuantity, truckLitersPerKm);

        Map<String, Vehicles> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", car);
        vehicles.put("Truck", truck);

        int n = Integer.parseInt(reader.readLine());
        DecimalFormat df = new DecimalFormat("#.##");
        while (n-- > 0) {
            String[] input = reader.readLine().split("\\s+");
            String command = input[0];
            String vehicleType = input[1];
            double parameter = Double.parseDouble(input[2]);
            switch (command) {
                case "Drive":
                    if (vehicles.get(vehicleType).drive(parameter)){
                        System.out.printf("%s travelled %s km%n", vehicleType, df.format(parameter));
                    }else {
                        System.out.printf("%s needs refueling%n", vehicleType);
                    }
                    break;
                case "Refuel":
                    vehicles.get(vehicleType).refuel(parameter);
                    break;
                default:
                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
    }
}
