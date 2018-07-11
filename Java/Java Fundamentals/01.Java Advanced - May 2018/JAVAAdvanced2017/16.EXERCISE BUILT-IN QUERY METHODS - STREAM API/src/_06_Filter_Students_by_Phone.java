import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _06_Filter_Students_by_Phone {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> students = new LinkedHashMap<>();

        //starting with 02 / +3592

        while (true) {
            String line = bf.readLine();
            if ("END".equalsIgnoreCase(line)) {
                break;
            }

            List<String> tokens = Arrays.stream(line.split("\\s+"))
                    .filter(s -> (s != null && !s.isEmpty())).collect(Collectors.toList());
            if (tokens.isEmpty()) {
                return;
            }

            String studentName = tokens.get(0) + " " + tokens.get(1);
            String phoneNumber = tokens.get(2);
            students.put(studentName, phoneNumber);
        }

        students.entrySet().stream()
                .filter(s -> (s.getValue().startsWith("02") || s.getValue().startsWith("+3592")))
                .forEach(s -> System.out.println(s.getKey()));
    }
}

//    Ashley Lee +35920412542
//    Michelle Daniels +35922450998
//    Charles Alvarez 02913942
//    Kathryn Lee 02496788
//    Nicholas Reyes 02758283
//    Amanda Hernandez 02667077
//    Catherine Porter 02533314
