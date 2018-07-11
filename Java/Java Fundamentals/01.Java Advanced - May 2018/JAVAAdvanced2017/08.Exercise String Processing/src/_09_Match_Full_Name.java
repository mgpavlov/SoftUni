package src;

import java.util.Scanner;          // 100/100
import java.util.regex.Pattern;

public class _09_Match_Full_Name {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String regex = "[A-Z]{1}[a-z]{1,} [A-Z]{1}[a-z]{1,}$";
        String text = scan.nextLine();

        while (!text.equals("end")) {
            if (Pattern.matches(regex, text)) {
                System.out.println(text);
            }
            text = scan.nextLine();
        }
    }
}
