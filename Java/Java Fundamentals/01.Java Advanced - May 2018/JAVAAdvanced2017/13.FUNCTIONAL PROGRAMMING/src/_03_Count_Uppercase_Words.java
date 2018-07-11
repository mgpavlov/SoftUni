import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // 100/100

public class _03_Count_Uppercase_Words {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s");
        List<String> specialWord = new ArrayList<>();

        Integer count = 0;
        for (String anInput : input) {
            Character first = anInput.charAt(0);

            if (first.toString().equals(first.toString().toUpperCase())) {
                count++;
                specialWord.add(anInput);
            }
        }
        System.out.println(specialWord.size());
        for (String aSpecialWord : specialWord) {
            System.out.println(aSpecialWord);
        }
    }
}
