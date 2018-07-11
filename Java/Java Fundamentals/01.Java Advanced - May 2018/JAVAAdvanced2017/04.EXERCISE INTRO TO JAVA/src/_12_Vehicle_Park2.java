import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;   // 100/100

public class _12_Vehicle_Park2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        List<String> list = Arrays.asList(input);
        ArrayList<String> vehicles = new ArrayList<>();
        vehicles.addAll(list);
        String[] commands = scanner.nextLine().toLowerCase().split(" ");
        int sold = 0;

        while (!commands[0].equals("end")) {
            String desiredVehicle = commands[0].charAt(0) + commands[2];

            if (vehicles.contains(desiredVehicle)) {
                int price = ((int) commands[0].charAt(0)) * Integer.parseInt(commands[2]);
                System.out.printf("Yes, sold for %d$\n", price);
                vehicles.remove(desiredVehicle);
                sold++;
            } else {
                System.out.println("No");
            }

            commands = scanner.nextLine().toLowerCase().split(" ");
        }
        System.out.println("Vehicles left: " + toString(vehicles));
        System.out.println("Vehicles sold: " + sold);
    }

    private static String toString(ArrayList<String> vehicles) {
        StringBuilder sb = new StringBuilder();
        if (!vehicles.isEmpty()) {
            for (String vehicle : vehicles) {
                sb.append(vehicle).append(", ");
            }
            sb.delete(sb.lastIndexOf(","), sb.length());
        }
        return sb.toString();
    }
}
