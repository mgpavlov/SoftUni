import java.util.Map;
import java.util.Scanner;  // 100/100
import java.util.TreeMap;

public class _10_Count_Symbols1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Character, Integer> occurrences = new TreeMap<>();

        String text = scanner.nextLine();

        for (int i = 0; i < text.length(); i++) {
            Character symbol = text.charAt(i);
            if (!occurrences.containsKey(symbol))
            {
                occurrences.put(symbol, 1);
            }
            else
            {
                Integer numberOfOccurrences = occurrences.get(symbol);
                occurrences.put(symbol, ++numberOfOccurrences);
            }
        }

        for (Map.Entry<Character, Integer> entry : occurrences.entrySet()) {
            System.out.printf("%s: %d time/s\n", entry.getKey(), entry.getValue());
        }
    }
}
