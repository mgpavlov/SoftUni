import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class p03StudentsByAge {
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
        students.stream().filter(a->{
            if (Integer.parseInt(a.split(" ")[2].trim())>=18 && Integer.parseInt(a.split(" ")[2].trim())<=24){
                return  true;
            }else{
                return false;
            }
        }).forEach(System.out::println);
        //students.forEach(System.out::println);
    }
}
