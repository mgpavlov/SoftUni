package p05SpeedRacing;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    private static LinkedHashMap<String, Car> Cars = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n; i++) {
            String[] tokens = scan.nextLine().split("\\s+");
            String model = tokens[0];
            Double fuelAmount = Double.parseDouble(tokens[1]);
            Double fuelCostPerKilometer = Double.parseDouble(tokens[2]);

            Car newCar = new Car(model, fuelAmount, fuelCostPerKilometer);
            Cars.put(model,newCar);
        }

        while (true) {
            String command = scan.nextLine();
            if ("End".equals(command)) {
                break;
            }
            String[] as = command.split("\\s+");
            if (as[0].equals("Drive")) {
                String currentModel = as[1];
                Double amountOfKilometers = Double.parseDouble(as[2]);

                if (Cars.containsKey(currentModel)) {
                    if (!Cars.get(currentModel).drive(amountOfKilometers)){
                        System.out.println("Insufficient fuel for the drive");
                    }
                }
            }
        }
        for (Car car : Cars.values()) {
            System.out.println(car);
        }

    }
}
