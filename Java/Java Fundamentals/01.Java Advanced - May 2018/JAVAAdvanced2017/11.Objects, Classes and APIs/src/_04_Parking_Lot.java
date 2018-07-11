import java.util.ArrayDeque;
import java.util.Scanner;  // 100/100

public class _04_Parking_Lot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        ArrayDeque<String> parkinglot = new ArrayDeque<>();

        while (true) {
            String input = scan.nextLine();
            if (input.equals("END")) {
                break;
            }
            String[] reminder = input.split(", ");
            if (reminder[0].equals("IN"))
            {
                if (!parkinglot.contains(reminder[1])){
                    parkinglot.add(reminder[1]);
                }

            } else if(reminder[0].equals("OUT")) {
                parkinglot.remove(reminder[1]);
            }
        }
        if (parkinglot.size() == 0) {
            System.out.printf("Parking Lot is Empty");
        } else {
            while (true) {
                if (parkinglot.size() == 0) {
                    break;
                }
                System.out.println(parkinglot.pop());
            }
        }
    }
}
