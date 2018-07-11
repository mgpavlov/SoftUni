import java.util.Scanner;

public class _01_Read_Input {                     // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String firstWord = scanner.next();
        String secondWord = scanner.next();
        Integer number1 = Integer.parseInt(scanner.next());
        Integer number2 = Integer.parseInt(scanner.next());
        Integer number3 = Integer.parseInt(scanner.next());
        String thirdWord = scanner.next();
        Integer sum = number1 + number2 + number3;

        System.out.printf("%s %s %s %d", firstWord, secondWord, thirdWord, sum);
    }
}
