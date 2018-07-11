import java.util.Scanner;  // 100/100

public class _04_Average_of_Doubles {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String x = scan.nextLine();
        if (x.equals("")){
            System.out.printf("No match");
            return;
        }
        String[] input = x.split("\\s+");

        if (input.length == 0) {
            System.out.printf("No match");
        } else {
            Double sum = 0.0;

            for (String anInput : input) {
                sum += Double.parseDouble(anInput);
            }
            double average = sum / input.length;

            System.out.printf("%.2f", average);
        }
    }
}
