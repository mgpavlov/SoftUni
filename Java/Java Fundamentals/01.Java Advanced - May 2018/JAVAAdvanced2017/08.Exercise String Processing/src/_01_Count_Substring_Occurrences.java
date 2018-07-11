package src;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;         // 80/100

public class _01_Count_Substring_Occurrences {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine().toLowerCase();
        String pattern = scan.nextLine();

     System.out.println(input.split(pattern, -1).length - 1);

//        Pattern patterns = Pattern.compile("232");
//        int count = countMatches(patterns, "23232");
//        System.out.println(count);
    }

    static int countMatches(Pattern pattern, String string)
    {
        Matcher matcher = pattern.matcher(string);

        int count = 0;
        int pos = 0;
        while (matcher.find(pos))
        {
            count++;
            pos = matcher.start() + 1;
        }

        return count;
    }
}
