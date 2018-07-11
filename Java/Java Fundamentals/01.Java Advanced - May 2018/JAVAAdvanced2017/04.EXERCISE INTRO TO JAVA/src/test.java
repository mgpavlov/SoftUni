import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] vehicles = scanner.nextLine().split(" ");
        int countSold = 0;
        while (true) {
            String newVech = scanner.nextLine();
            if (newVech.equals("End of customers!")) {
                break;
            }
            char type = newVech.charAt(0);
            int seats = Integer.parseInt(newVech.split(" ")[2]);
            String car = (type + "" + seats).toLowerCase();
            boolean sold = false;

            for (int i = 0; i < vehicles.length; i++) {
                if (car.equals(vehicles[i])) {
                    int price = (type + 32) * seats;
                    vehicles[i] = "";
                    countSold++;
                    System.out.printf("Yes, sold for %d$\n", price);
                    sold = true;
                    break;
                }
            }
            if (!sold) {
                System.out.println("No");
            }
        }

        System.out.printf("Vehicles left: ");
        StringBuilder output = new StringBuilder();
        for (String v : vehicles) {
            if (!v.isEmpty()) {
                output.append(v).append(", ");
            }
        }
        System.out.println(output);
        System.out.println("Vehicles sold: " + countSold);

    }
}
