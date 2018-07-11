import java.util.Scanner;

public class _02_Average_of_Three_Numbers {    // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Double first = Double.parseDouble(scanner.next());
        Double secound = Double.parseDouble(scanner.next());
        Double third = Double.parseDouble(scanner.next());

        Double average = (first + secound + third )/ 3;

        System.out.printf("%.2f",average);
    }
}
