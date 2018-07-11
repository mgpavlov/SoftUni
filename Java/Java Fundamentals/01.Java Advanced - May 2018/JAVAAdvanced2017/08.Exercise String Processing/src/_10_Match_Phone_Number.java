package src;

import java.util.Scanner;          // 100/100
import java.util.regex.Pattern;

public class _10_Match_Phone_Number {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String text = scan.nextLine();
        String regex = "\\+359( |-)2\\1\\d{3}\\1\\d{4}$";
  //   String pattern= "^\\+359([\\s-])2\\1([0-9]){3}\\1([0-9]){4}$";= "^\\+359([\\s-])2\\1([0-9]){3}\\1([0-9]){4}$";

        while (!text.equals("end")) {
            if (Pattern.matches(regex, text)) {
                System.out.println(text);
            }
            text = scan.nextLine();
        }
    }
}
