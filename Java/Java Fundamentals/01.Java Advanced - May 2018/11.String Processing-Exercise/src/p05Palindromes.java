import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class p05Palindromes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        String[] input = str.split("[.,?!\\s+]");
        Set<String> result = new TreeSet<>();
        for (String word : input) {
            if (!word.equals("")){
                if (IsWordPalindrome(word))
                result.add(word);
            }
        }
        System.out.println(result.toString());
    }

    private static boolean IsWordPalindrome(String word) {

        StringBuilder palindrome = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != word.charAt(word.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}
