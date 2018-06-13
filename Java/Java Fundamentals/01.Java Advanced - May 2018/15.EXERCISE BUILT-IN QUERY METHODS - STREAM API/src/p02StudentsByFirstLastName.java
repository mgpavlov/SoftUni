import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

public class p02StudentsByFirstLastName {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<String> students = new LinkedHashSet<>();
        while (true) {
            String input = reader.readLine();
            if (input.equals("END")) {
                break;
            }
            //if (input.split(" ")[0].compareTo(input.split(" ")[1])<0){
                students.add(input);
            //}
        }
        students.stream().filter(a->{
            return a.split(" ")[0].compareTo(a.split(" ")[1])<0;
        }).forEach(System.out::println);
        //students.forEach(System.out::println);
    }
}
