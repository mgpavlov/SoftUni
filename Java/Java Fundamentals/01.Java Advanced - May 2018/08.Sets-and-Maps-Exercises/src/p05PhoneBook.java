import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p05PhoneBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        Map<String, String> phoneBook = new LinkedHashMap<>();
        if (line.equals("stop")) {
            return;
        }

        while (!"search".equals(line)) {
            if (line.equals("stop")) return;
            String name = line.split("-")[0];
            String number = line.split("-")[1];

            phoneBook.put(name, number);
            line = sc.nextLine();
        }

        while (true) {
            line = sc.nextLine();
            if (line.equals("stop")) return;

            if (phoneBook.containsKey(line)) {
                System.out.println(line + " -> " + phoneBook.get(line));
            } else {
                System.out.printf("Contact %s does not exist.%n", line);
            }
        }
    }
}
