import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.LinkedHashSet;
        import java.util.Set;

public class p06FilterStudentsByPhone {
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
                .filter(a -> {
                    return a.split(" ")[2].trim().startsWith("02") || a.split(" ")[2].trim().startsWith("+3592");
                })
                .forEach(s -> {
                    System.out.println(s.split(" ")[0].trim() + " " + s.split(" ")[1].trim());
                });
    }
}
