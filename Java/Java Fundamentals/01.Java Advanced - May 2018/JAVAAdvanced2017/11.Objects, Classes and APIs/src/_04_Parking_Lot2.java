import java.util.HashSet;
import java.util.Scanner; // 100/100

public class _04_Parking_Lot2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<String> parkingLot = new HashSet<String>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("END")) {
                break;
            } else {
                String[] reminder = input.split(", ");
                if (reminder[0].equals("IN")) {
                    parkingLot.add(reminder[1]);
                } else {
                    parkingLot.remove(reminder[1]);
                }
            }
        }

        if (parkingLot.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            for (String carNumber : parkingLot) {
                System.out.println(carNumber);
            }
        }
    }
}