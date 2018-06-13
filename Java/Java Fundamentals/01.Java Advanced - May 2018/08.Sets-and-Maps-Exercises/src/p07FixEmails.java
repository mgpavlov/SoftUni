import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p07FixEmails {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, String> result = new LinkedHashMap<>();

        while (true){
            String line = sc.nextLine();
            if (line.equals("stop")){
                break;
            }
            String name = line;
            String email = sc.nextLine();

            result.put(name, email);
        }
        for (Map.Entry<String, String> nameEmail : result.entrySet()) {
            if (!(nameEmail.getValue().endsWith("us")||nameEmail.getValue().endsWith("uk")||nameEmail.getValue().endsWith("com")))
            System.out.printf("%s -> %s%n", nameEmail.getKey(), nameEmail.getValue());
        }
    }
}
