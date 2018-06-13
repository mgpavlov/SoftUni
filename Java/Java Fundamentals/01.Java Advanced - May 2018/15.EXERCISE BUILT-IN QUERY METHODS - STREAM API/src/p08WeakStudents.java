import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;

public class p08WeakStudents {
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
                .filter(x->{return x.contains("2")|| x.contains("3");})
                .filter(x->{
                    x = x.replaceAll("2", "Z");
                    x = x.replaceAll("3", "Z");
                    String[] temp = x.split(" ");
                    int count = 0;
                    for (int i = 2; i < temp.length; i++) {
                        if (temp[i].equals("Z")){
                            count++;
                        }
                    }
                    return count >= 2;
                })
                .forEach(s -> {
                    System.out.println(s.split(" ")[0].trim() + " " + s.split(" ")[1].trim());
                });
    }
}
