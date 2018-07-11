package src;

import java.io.BufferedReader;
import java.io.IOException;           // 100/100
import java.io.InputStreamReader;

public class _01_Count_Substring_Occurrences2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String text = reader.readLine().toLowerCase();
        String word = reader.readLine().toLowerCase();

        int counter = 0;
        int index = text.indexOf(word);

        while (index != -1) {
            counter++;
            index = text.indexOf(word, index + 1);
        }
        System.out.println(counter);
    }
}