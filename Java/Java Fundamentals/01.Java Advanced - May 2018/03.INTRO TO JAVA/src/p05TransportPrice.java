import java.util.Scanner;

public class p05TransportPrice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double km = Double.parseDouble(sc.nextLine());
        String dayOrNight = sc.nextLine();
        double price = 0;
        if ("day".equals(dayOrNight) & km <20){
            price = 0.70+0.79*km;
        }else if ("night".equals(dayOrNight) & km <20){
            price = 0.70+0.90*km;
        }else if (km<100){
            price = km*0.09;
        }else {
            price = km*0.06;
        }

        System.out.printf("%.2f", price);
    }
}
