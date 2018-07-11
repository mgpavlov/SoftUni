import java.util.List;
import java.util.Scanner; // 100/100

public class _02_Sum_Numbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String ivan = scanner.nextLine();
        String[] numbers = ivan.split(", ");

        Integer sum = 0;

        for (String number : numbers) {
            sum += Integer.parseInt(number);
        }

        System.out.printf("Count = %d\n", numbers.length);
        System.out.printf("Sum = %d", sum);
    }

    private static Integer sumNumbers(List<String> myArrayList) {
        Integer sum = 0;

        for (String number : myArrayList) {
            sum += Integer.parseInt(number);
        }
        return sum;
    }
}
