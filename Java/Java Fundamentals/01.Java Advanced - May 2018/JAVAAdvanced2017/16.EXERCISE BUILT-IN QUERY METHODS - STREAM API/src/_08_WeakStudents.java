import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class _08_WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, List<Integer>> students = new LinkedHashMap<>();

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
            List<Integer> grades = tokens.subList(2, tokens.size()).stream()
                    .map(Integer::valueOf).collect(Collectors.toList());
            students.put(studentName, grades);
        }

        students.entrySet().stream()
                .filter(filterStudentsWithAtLeastTwoMarksUnderOrEqualToNumber())
                .forEach(s -> System.out.println(s.getKey()));
    }

    private static Predicate<Map.Entry<String, List<Integer>>> filterStudentsWithAtLeastTwoMarksUnderOrEqualToNumber() {
        return s -> {
            List<Integer> grades = s.getValue();
            int count = 0;
            for (Integer grade : grades) {
                if (grade <= 3) {
                    count++;
                }
            }
            return count >= 2;
        };
    }
}

//         Jeffrey Gibson 2 2 3 3
//        Daniel Ferguson 2 2 3 3
//        Lawrence Welch 2 2 3 4
//        Michelle Daniels 2 3 3 3
//        Susan Dixon 2 2 2 5
//        Nicholas Reyes 2 2 3 4
//        Roger Adams 2 2 3 4
//        Nancy Patterson 2 2 2 6
//        Victor Harvey 2 2 3 5
//        Marilyn Price 2 2 3 5
//        Fred Andrews 2 3 3 4
//        James Nelson 3 3 3 3
//        Edward Rose 2 3 4 4
//        Alice Baker 2 2 4 5
//        Frances Fisher 2 3 3 5
//        Eugene Lawson 2 2 4 5
//        Juan Ferguson 2 3 3 5
//        Stephen Roberts 2 3 4 4
//        Raymond Stewart 2 3 3 5
//        Ernest Banks 2 2 4 5
//        Richard Edwards 2 3 4 5
//        Jane Lopez 3 3 3 5
//        Lawrence Hernandez 2 3 4 5
//        Melissa Robinson 2 3 3 6
//        Jacqueline Stewart 2 3 4 5
//        Emily Howard 2 3 3 6
//        Lawrence Evans 3 3 3 5
//        Amanda Hernandez 2 2 4 6
//        Helen Williamson 2 2 4 6
//        Daniel Carter 2 3 4 6
//        William Alexander 2 3 5 5
//        Cheryl Gray 2 2 5 6
//        Gary Foster 3 3 4 5
//        Sean Castillo 2 3 5 5
//        Heather Knight 2 3 4 6
//        Kelly Woods 2 3 5 5
//        Charles Alvarez 2 2 5 6
//        Catherine Porter 2 3 5 5
//        Billy Bowman 2 3 4 6
//        Julie Riley 2 3 5 5
//        Justin Reyes 2 3 4 6
//        Beverly Clark 2 3 5 6
//        Elizabeth James 2 2 6 6
//        Ruby Lewis 3 3 4 6
//        Aaron Gibson 3 3 5 6
//        Jesse Howard 2 3 6 6
//        Theresa Simpson 2 3 6 6
//        Edward Nelson 3 3 5 6
//        Terry Pierce 3 3 6 6
//        Antonio Rodriguez 3 3 6 6

