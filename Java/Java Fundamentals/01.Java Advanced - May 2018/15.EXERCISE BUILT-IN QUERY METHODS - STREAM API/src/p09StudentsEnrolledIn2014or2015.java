import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class p09StudentsEnrolledIn2014or2015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<String> students = new LinkedHashSet<>();
        while (true) {
            String input = reader.readLine();
            if (input.equals("END")) {
                break;
            }
            students.add(input);
        }
        students.stream()
                .filter(x -> {
                    return x.split(" ")[0].endsWith("14") || x.split(" ")[0].endsWith("15");
                })
                .forEach(s -> {
                    for (int i = 1; i < s.split(" ").length; i++) {
                        System.out.print(s.split(" ")[i].trim() + " ");
                    }
                    System.out.println();
                });
    }
}
