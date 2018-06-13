import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class p04CountSymbols {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map <Character, Integer> output = new TreeMap<>();

        char[] input = sc.nextLine().toCharArray();
        for (char letter : input) {
            if (!output.containsKey(letter)){
                output.put(letter, 0);
            }
            output.put(letter, output.get(letter)+1);
        }

        for (Map.Entry<Character, Integer> character : output.entrySet()) {
            System.out.println(character.getKey()+": "+character.getValue()+" time/s");
        }

    }
}
