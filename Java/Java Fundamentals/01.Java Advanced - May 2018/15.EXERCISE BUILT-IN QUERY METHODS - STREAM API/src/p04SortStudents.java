import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class p04SortStudents {
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
        students.stream().sorted((a,b)->{
            if (a.split(" ")[1].trim().compareTo(b.split(" ")[1].trim()) == 0){
                return b.split(" ")[0].trim().compareTo(a.split(" ")[0].trim());
            }else{
                return a.split(" ")[1].trim().compareTo(b.split(" ")[1].trim());
            }
        }).forEach(System.out::println);
    }
}
