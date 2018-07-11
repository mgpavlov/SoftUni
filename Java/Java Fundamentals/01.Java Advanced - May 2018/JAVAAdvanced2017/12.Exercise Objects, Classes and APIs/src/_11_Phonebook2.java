import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.HashMap;
import java.util.Map;

public class _11_Phonebook2 {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, String> phonebook = new HashMap<>();

        while (true) {
            String[] entry = reader.readLine().split("-");
            if (entry[0].equalsIgnoreCase("search")) {
                break;
            }
            phonebook.put(entry[0], entry[1]);
        }

        while (true) {
            String key = reader.readLine();
            if (key.equalsIgnoreCase("stop")) {
                break;
            }

            if (phonebook.containsKey(key)) {
                System.out.printf("%s -> %s\n", key, phonebook.get(key));
            } else {
                System.out.printf("Contact %s does not exist.\n", key);
            }
        }
    }
}
