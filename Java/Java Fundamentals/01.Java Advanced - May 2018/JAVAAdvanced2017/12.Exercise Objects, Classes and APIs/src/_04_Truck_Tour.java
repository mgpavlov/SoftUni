import java.util.Scanner;

public class _04_Truck_Tour {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int min = Integer.MAX_VALUE;


        for (int i = 0; i < n; i++) {
            String[] input = scan.nextLine().split("\\s+");
            Integer Curent = Integer.parseInt(input[0]);

            if (Curent < min) {
                min = Curent;
            }
        }
        System.out.printf("%d", min);
    }
}