import java.util.Scanner; // 100/100

public class _01_Calculate_Triangle_Area_Method {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Double base = Double.parseDouble(scan.next());
        Double height = Double.parseDouble(scan.next());

        Double area = calculateArea(base, height);
        System.out.printf("Area = %.2f", area);

    }

    private static Double calculateArea(Double base, Double height)
    {
        return base * height / 2;
    }
}
