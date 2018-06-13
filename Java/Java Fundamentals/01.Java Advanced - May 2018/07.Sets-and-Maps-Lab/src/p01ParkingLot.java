import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class p01ParkingLot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        Set<String> carsInParking = new HashSet<>();

        while (!"END".equals(line)) {
            String direction = line.split(", ")[0];
            String carNumber = line.split(", ")[1];
            if ("IN".equals(direction)){
                carsInParking.add(carNumber);
            }else{
                carsInParking.remove(carNumber);
            }
            line = sc.nextLine();
        }
        if (carsInParking.isEmpty()) System.out.println("Parking Lot is Empty");
        else System.out.println(String.join("\n", carsInParking));
    }
}
