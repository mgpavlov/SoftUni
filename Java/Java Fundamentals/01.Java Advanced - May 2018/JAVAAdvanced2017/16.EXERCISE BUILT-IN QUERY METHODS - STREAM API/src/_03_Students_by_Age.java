import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class _03_Students_by_Age {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> students = new LinkedHashMap<>();

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
            int age = Integer.valueOf(tokens.get(2));
            students.put(studentName, age);
        }

        //Map<String, String> filteredStudents =
        students.entrySet().stream()
                .filter(s -> (18 <= s.getValue() && s.getValue() <= 24))
                .forEach(s -> System.out.println(s.getKey() + " " + s.getValue()));
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println();
    }
}


//        Mildred Hansen 22
//        Craig Ellis 21
//        Andrea Harper 23
//        Richard Edwards 20
//        Margaret Peterson 19
//        Jonathan Gibson 21
//        Daniel Ferguson 21
//        Craig Flores 22
//        Jesse Howard 18
//        Heather Knight 20
//        Bonnie Shaw 23
//        Marie Simpson 24
//        Michelle Daniels 19
//        Terry Pierce 18
//        Theresa Simpson 21
//        Amanda Hernandez 24
//        Stephen Roberts 24
//        William Garcia 18
//        Billy Bowman 24
//        David Johnston 23
//        Cynthia Olson 23
//        Fred Andrews 20
//        Diana Flores 23
//        Justin Reyes 23

