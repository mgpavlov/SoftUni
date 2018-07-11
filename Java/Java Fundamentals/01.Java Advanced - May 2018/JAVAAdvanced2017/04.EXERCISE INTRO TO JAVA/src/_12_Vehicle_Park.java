import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _12_Vehicle_Park {                  // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        String[] vehicles = scanner.nextLine().split("\\s+");
        list.addAll(Arrays.asList(vehicles));

        Integer counter = 0;

        while (true) {
            String current = scanner.nextLine();
            if (current.equals("End of customers!")) {
                break;
            }
            String[] curentCar = current.split("\\s+");
            int quantity = Integer.parseInt(curentCar[2]);
            char typeVehicle = curentCar[0].toLowerCase().charAt(0);
            String searchedCar = "" + typeVehicle + quantity;

            if (list.contains(searchedCar)) {
                int sum = typeVehicle * quantity;
                list.remove(searchedCar);

                System.out.printf("Yes, sold for %d$\n", sum);
                counter++;
            } else {
                System.out.printf("No\n");
            }
        }
        System.out.printf("Vehicles left: ");
        System.out.printf("%s%n", list.toString().substring(1, list.toString().length() - 1));
        System.out.printf("Vehicles sold: %d", counter);
    }
}
