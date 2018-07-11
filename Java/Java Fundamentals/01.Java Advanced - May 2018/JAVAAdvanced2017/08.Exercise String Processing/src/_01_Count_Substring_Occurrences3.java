package src;

import java.io.BufferedReader;
import java.io.IOException;   // 100/100
import java.io.InputStreamReader;

public class _01_Count_Substring_Occurrences3 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String stack = reader.readLine();
        String needle = reader.readLine();

        int counter = 0;
        for (int i = 0; i < stack.length() - needle.length() + 1; i++) {
            if (needle.equalsIgnoreCase(stack.substring(i, i + needle.length()))) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}