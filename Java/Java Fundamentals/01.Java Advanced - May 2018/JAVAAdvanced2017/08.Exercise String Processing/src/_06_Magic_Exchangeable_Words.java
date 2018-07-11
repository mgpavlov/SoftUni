package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class _06_Magic_Exchangeable_Words {
    public static void main(String[] args) throws IOException, IOException {

        BufferedReader scan = new BufferedReader(new InputStreamReader(System.in));
        String[] input = scan.readLine().split(" ");

        if(input[0].length() > input[1].length())
        {
            System.out.println(ExchangableWords(input[1], input[0]));
        }
        else
        {
            System.out.println(ExchangableWords(input[0], input[1]));
        }
    }

    private static boolean ExchangableWords(String s, String s1) {

        HashMap<Character, Character> store = new HashMap<>();

        StringBuilder word1 = new StringBuilder();
        StringBuilder word2 = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if(!word1.toString().contains(s.charAt(i) + ""))
            {
                word1.append(s.charAt(i));
            }
            if (store.containsKey(s.charAt(i)))
            {
                if (store.get(s.charAt(i)) != s1.charAt(i)) {
                    return false;
                }
            }
            else
            {
                store.put(s.charAt(i), s1.charAt(i));
            }
        }

        for (int i = 0; i < s1.length(); i++)
        {
            if(!word2.toString().contains(s1.charAt(i) + ""))
            {
                word2.append(s1.charAt(i));
            }
        }

        return word1.length() == word2.length();
    }
}
