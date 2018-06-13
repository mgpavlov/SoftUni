import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class p01StudentsByGroup {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<String> students = new LinkedHashSet<>();
        while (true) {
            String input = reader.readLine();
            if (input.equals("END")) {
                break;
            }
            if (input.trim().endsWith("2")) {
                students.add(input.trim().substring(0, input.trim().length() - 1).trim());
            }
        }
        students.stream().sorted(Comparator.comparing(x -> x.split(" ")[0])).forEach(System.out::println);
    }
}
