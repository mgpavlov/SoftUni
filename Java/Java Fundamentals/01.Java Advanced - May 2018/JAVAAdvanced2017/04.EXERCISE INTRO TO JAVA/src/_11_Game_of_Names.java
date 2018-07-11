import java.util.Scanner;

public class _11_Game_of_Names {                // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = Integer.parseInt(scanner.nextLine());

        Integer min = Integer.MIN_VALUE;
        String winner = "";

        for (int i = 0; i < n; i++) {
            String currentName = scanner.nextLine();
            Integer initialPoits = Integer.parseInt(scanner.nextLine());

            Integer sum = sumOfDigits(currentName) + initialPoits;
            if (sum > min) {
                min = sum;
                winner = currentName;
            }
        }
        System.out.printf("The winner is %s - %d points", winner, min);
    }

    private static Integer sumOfDigits(String playerName) {

        Integer result = 0;
        for (int i = 0; i < playerName.length(); i++) {
            char currentChar = playerName.charAt(i);
            if (currentChar % 2 == 0) {
                result += currentChar;
            }
            else {
                result -= currentChar;
            }
        }

        return result;
    }
}
