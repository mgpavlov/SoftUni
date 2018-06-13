import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class p03FirstName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = Arrays.asList(reader.readLine().split("\\s+"));
        Set<Character> letters = new HashSet<>();

        Stream.of(reader.readLine().split("\\s+")).map(s->s.toLowerCase().charAt(0)).forEach(letters::add);

        Optional<String> firstName = names.stream().filter(s->letters.contains(s.toLowerCase().charAt(0))).sorted().findFirst();

        if (firstName.isPresent()){
            System.out.println(firstName.get());
        }else{
            System.out.println("No match");
        }

    }
}
