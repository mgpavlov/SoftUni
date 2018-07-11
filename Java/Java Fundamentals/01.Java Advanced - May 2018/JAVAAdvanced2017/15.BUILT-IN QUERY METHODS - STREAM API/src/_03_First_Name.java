import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*; // 100/100

public class _03_First_Name {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = Arrays.asList(bf.readLine().split("\\s+"));
        Set<Character> letters = new HashSet<>();

        Arrays.stream(bf.readLine().split("\\s+"))
                .map(s -> s.toUpperCase().charAt(0))
                .forEach(letters::add);

        Optional<String> firstName = names.stream()
                .filter(s -> letters.contains(s.charAt(0)))
                .sorted()
                .findFirst();

        System.out.println(firstName.orElse("No match"));
    }
}
