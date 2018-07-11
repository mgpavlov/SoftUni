import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 20/100
import java.util.LinkedHashMap;
import java.util.Map;

public class _05_Filter_By_Age {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());

        LinkedHashMap<Integer, String> people = new LinkedHashMap<>();

        for (int i = 0; i < count; i++) {
            String[] inputLine = reader.readLine().split(", ");
            String name = inputLine[0];
            Integer age = Integer.parseInt(inputLine[1]);

            people.put(age, name);
        }

        String condition = reader.readLine();
        int ageLimit = Integer.parseInt(reader.readLine());
        String format = reader.readLine();
    }
}
