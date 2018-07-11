import java.util.Scanner;

public class _05_Transport_Price {                   //100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer distance = Integer.parseInt(scanner.nextLine());
        String timeOfDay = scanner.nextLine();
        Double sum = 0.0;

        if (distance < 20) {
            Double initialTax = 0.7;
            double timeCost = 0.0;
            if (timeOfDay.equals("day"))
            {
                timeCost = 0.79;
            }
            else if (timeOfDay.equals("night"))
            {
                timeCost = 0.9;
            }
            sum = initialTax + distance * timeCost;

        }
        else if (distance >= 20 && distance < 100)
        {
            sum =  distance * 0.09;
        }
        else if ( distance >= 100)
        {
            sum =  distance * 0.06;
        }

        System.out.printf("%.2f", sum);
    }
}
