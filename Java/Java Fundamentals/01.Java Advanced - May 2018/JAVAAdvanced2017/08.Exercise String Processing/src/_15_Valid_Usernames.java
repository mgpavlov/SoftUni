package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;  // 100/100
import java.util.regex.Pattern;

public class _15_Valid_Usernames {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Pattern pattern = Pattern.compile("(?<=[ \\/\\\\() ]|^)[a-zA-Z][a-zA-Z0-9_]{2,25}(?=[ \\/\\\\()]|$)");

        String input = scan.nextLine();
        List<String> names = new ArrayList<>();

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            names.add(matcher.group());
        }

        int maxLenght = -1;
        int firstIndex = 0;

        for (int i = 0; i < names.size() - 1; i++) {
            int currentLenght = names.get(i).length() + names.get(i + 1).length();
            if (currentLenght > maxLenght) {
                maxLenght = currentLenght;
                firstIndex = i;
            }
        }
        System.out.printf("%s\n%s", names.get(firstIndex), names.get(firstIndex + 1));
    }
}