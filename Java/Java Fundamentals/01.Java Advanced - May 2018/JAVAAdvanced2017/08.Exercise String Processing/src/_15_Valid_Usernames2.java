package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _15_Valid_Usernames2 {
    private static String str1 = "";
    private static String str2 = "";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String firstUsername = "";
        String secondUsername = "";

        String[] input = in.readLine().split("\\\\\\s*|\\/\\s*|\\(\\s*|\\)\\s*|\\s+");

        Pattern pattern = Pattern.compile("^[A-Za-z]\\w{2,24}$");
        for (String username : input) {
            if (!input.equals("")) {
                Matcher matcher = pattern.matcher(username);

                if (matcher.find()) {
                    if (str1.equals("")) {
                        str1 = username;
                        continue;
                    }

                    if (!str1.equals("") && str2.equals("")) {
                        str2 = username;
                    }

                    if ((!str1.equals("") && !str2.equals("")) && (str1.length() + str2.length() > firstUsername.length() + secondUsername.length())) {
                        firstUsername = str1;
                        secondUsername = str2;
                    }

                    if (!str1.equals("") && !str2.equals("")) {
                        str1 = str2;
                        str2 = "";
                    }
                }
            }
            System.out.println(firstUsername);
            System.out.println(secondUsername);
        }
    }
}
