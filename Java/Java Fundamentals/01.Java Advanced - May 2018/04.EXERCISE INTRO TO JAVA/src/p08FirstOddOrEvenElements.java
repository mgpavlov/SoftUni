import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class p08FirstOddOrEvenElements {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] area = sc.nextLine().split("\\s+");
        String[] command = sc.nextLine().split("\\s+");
        int n = Integer.parseInt(command[1]);
        int counter = 1;

        if (command[2].equals("odd")) {
            for (String anArea : area) {
                if (n >= counter && Integer.parseInt(anArea) % 2 != 0) {
                    System.out.printf("%d ", Integer.parseInt(anArea));
                    counter++;
                }
            }
        }
        else if (command[2].equals("even")) {
            for (String anArea : area) {
                if (n >= counter && Integer.parseInt(anArea) % 2 == 0) {
                    System.out.printf("%d ", Integer.parseInt(anArea));
                    counter++;
                }

            }
        }

    }
}
