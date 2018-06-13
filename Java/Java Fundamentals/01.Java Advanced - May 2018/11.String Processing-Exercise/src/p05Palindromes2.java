import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

public class p05Palindromes2 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().split("[ !?.,]");
        Set<String> order = new TreeSet<>();

        for (String word : input) {
            StringBuilder current = new StringBuilder(word);
            current.reverse();

            if (word.equals(current.toString()) && !word.equals("")) {
                order.add(word);
            }
        }
        System.out.print(order);
    }
}
