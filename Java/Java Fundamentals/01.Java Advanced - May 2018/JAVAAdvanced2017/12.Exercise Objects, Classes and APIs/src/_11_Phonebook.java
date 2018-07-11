import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.HashMap;
import java.util.Map;

public class _11_Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, String> myPhonebook = new HashMap<>();

        readInput(bf, myPhonebook);
        searchInPhoneBook(bf, myPhonebook);
    }

    private static void searchInPhoneBook(BufferedReader bf, Map<String, String> myPhonebook) throws IOException {
        while (true) {
            String name = bf.readLine();

            if ("Stop".equalsIgnoreCase(name)) {
                break;
            }

            if (myPhonebook.containsKey(name)) {
                System.out.printf("%s -> %s%n", name, myPhonebook.get(name));
            } else {
                System.out.printf("Contact %s does not exist.%n", name);
            }
        }
    }

    private static void readInput(BufferedReader bf, Map<String, String> myPhonebook) throws IOException {
        while (true) {
            String[] line = bf.readLine().split("-");

            if ("search".equalsIgnoreCase(line[0])) {
                break;
            }

            String name = line[0];
            String phoneNumber = line[1];

            myPhonebook.put(name, phoneNumber);
        }
    }
}