package src;

import java.util.Scanner;
import java.util.regex.Matcher;   // 100/100
import java.util.regex.Pattern;

public class _09_Match_Full_Name2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String pattern = "^[A-Z][a-z]+\\s[A-Z][a-z]+$";

        while(true) {
            String line = scanner.nextLine();
            if("end".equals(line)){
                break;
            }
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(line);

            if(matcher.find()){
                System.out.println(line);
            }
        }
    }
}
