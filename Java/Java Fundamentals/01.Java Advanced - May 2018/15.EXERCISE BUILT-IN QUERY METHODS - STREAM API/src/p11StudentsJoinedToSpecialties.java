import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p11StudentsJoinedToSpecialties {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<String>> specialties = new HashMap<>();

        String input = reader.readLine();
        while (!"Students:".equals(input)) {
            List<String> tokens = Arrays.stream(input.split("\\s+")).collect(Collectors.toList());
            String facultyNumber = tokens.get(tokens.size() - 1);
            tokens.remove(tokens.size() - 1);
            String specialty = String.join(" ", tokens);

            specialties.putIfAbsent(facultyNumber, new LinkedList<>());
            specialties.get(facultyNumber).add(specialty);

            input = reader.readLine();
        }

        Set<Students> students = new HashSet<>();

        input = reader.readLine();
        while (!"END".equals(input)) {
            List<String> tokens = Arrays.stream(input.split("\\s+")).collect(Collectors.toList());
            String facultyNum = tokens.get(0);
            tokens.remove(0);
            String studentName = String.join(" ", tokens);

            students.add(new Students(studentName, facultyNum));

            input = reader.readLine();
        }

        students.stream()
                .sorted(Comparator.comparing(Students::getName))
                .forEach(s -> {
                    List<String> sp = specialties.get(s.getFacultyNumber());

                    if (sp != null) {
                        sp.forEach(spet -> System.out.printf("%s %s %s%n"
                                , s.getName()
                                , s.getFacultyNumber()
                                , spet));
                    }
                });
    }

    private static class Students {

        String name;
        String facultyNumber;

        Students(String name, String facultyNumber) {
            this.name = name;
            this.facultyNumber = facultyNumber;
        }

        String getName() {
            return this.name;
        }

        String getFacultyNumber() {
            return this.facultyNumber;
        }
    }
}
