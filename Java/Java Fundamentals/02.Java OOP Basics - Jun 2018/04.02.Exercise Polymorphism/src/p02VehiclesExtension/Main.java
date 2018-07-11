package p02VehiclesExtension;

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
        double carTankCapacity = Double.parseDouble(carInfo[3]);
        Vehicles car = null;
        try {
            car = new Car(carFuelQuantity, carLitersPerKm, carTankCapacity);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        String[] truckInfo = reader.readLine().split("\\s+");
        double truckFuelQuantity = Double.parseDouble(truckInfo[1]);
        double truckLitersPerKm = Double.parseDouble(truckInfo[2]);
        double truckTankCapacity = Double.parseDouble(truckInfo[3]);
        Vehicles truck = null;
        try{
            truck = new Truck(truckFuelQuantity, truckLitersPerKm, truckTankCapacity);
        }catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        String[] busInfo = reader.readLine().split("\\s+");
        double busFuelQuantity = Double.parseDouble(busInfo[1]);
        double busLitersPerKm = Double.parseDouble(busInfo[2]);
        double busTankCapacity = Double.parseDouble(busInfo[3]);
        Vehicles bus = null;
        try{
            bus = new Bus(busFuelQuantity, busLitersPerKm, busTankCapacity);
        }catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }


        Map<String, Vehicles> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", car);
        vehicles.put("Truck", truck);
        vehicles.put("Bus", bus);

        int n = Integer.parseInt(reader.readLine());
        DecimalFormat df = new DecimalFormat("#.##");

        while (n-- > 0) {
            String[] input = reader.readLine().split("\\s+");
            String command = input[0];
            String vehicleType = input[1];
            double parameter = Double.parseDouble(input[2]);
            switch (command) {
                case "Drive":
                    if (vehicles.get(vehicleType).drive(parameter)) {
                        System.out.printf("%s travelled %s km%n", vehicleType, df.format(parameter));
                    } else {
                        System.out.printf("%s needs refueling%n", vehicleType);
                    }
                    break;
                case "Refuel":
                    try{
                        vehicles.get(vehicleType).refuel(parameter);
                    }catch (IllegalArgumentException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "DriveEmpty":
                    ((Bus) bus).isEmpty = true;
                    bus.setLittersPerKm(bus.getLittersPerKm());
                    if (vehicles.get(vehicleType).drive(parameter)) {
                        System.out.printf("%s travelled %s km%n", vehicleType, df.format(parameter));
                    } else {
                        System.out.printf("%s needs refueling%n", vehicleType);
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.printf("Car: %.2f%n", car != null ? car.getFuelQuantity() : 0);
        System.out.printf("Truck: %.2f%n", truck != null ? truck.getFuelQuantity() : 0);
        System.out.printf("Bus: %.2f%n", bus != null ? bus.getFuelQuantity() : 0);
    }
}
