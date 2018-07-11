import java.util.Scanner;  // 100/100

public class _02_Upper_Strings {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[]input=scan.nextLine().split("\\s+");

        for (String anInput : input) {
            System.out.printf("%s ", anInput.toUpperCase());
        }
    }
}
