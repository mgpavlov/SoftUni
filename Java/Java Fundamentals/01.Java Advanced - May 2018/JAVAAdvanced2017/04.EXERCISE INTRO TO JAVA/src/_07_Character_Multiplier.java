import java.util.Scanner;

public class _07_Character_Multiplier {  // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.next();
        String second = scanner.next();

        Integer minLenght = Math.min(first.length(), second.length());

        long result = 0;
        for (int i = 0; i < minLenght; i++) {
            result += first.charAt(i) * second.charAt(i);
        }

        String longer = "";
        if (first.length() > second.length()) {
            longer = first;
        } else {
            longer = second;
        }
        for (int i = minLenght; i < longer.length(); i++) {
            result += longer.charAt(i);
        }
        System.out.printf("%d", result);
    }
}
