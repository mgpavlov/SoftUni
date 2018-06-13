import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class p02SplitByWordCasing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("[,;:.!()\"'/\\[\\] \\\\]");

        List<String> lowerCaseWords = new ArrayList<>();
        List<String> upperCaseWords = new ArrayList<>();
        List<String> mixedCaseWords = new ArrayList<>();

        for (String word : input) {
            if (word.length() == 0) {
                continue;
            }
            boolean isMixed = false;

            for (int i = 0; i < word.length(); i++) {
                if (!Character.isLetter(word.charAt(i))) {
                    mixedCaseWords.add(word);
                    isMixed = true;
                    break;
                }
            }

            if (!isMixed) {
                if (word.toLowerCase().equals(word)) {
                    lowerCaseWords.add(word);
                } else if (word.toUpperCase().equals(word)) {
                    upperCaseWords.add(word);
                } else {
                    mixedCaseWords.add(word);
                }
            }

        }

        System.out.printf("Lower-case: %s", String.join(", ", lowerCaseWords));
        System.out.println();
        System.out.printf("Mixed-case: %s", String.join(", ", mixedCaseWords));
        System.out.println();
        System.out.printf("Upper-case: %s", String.join(", ", upperCaseWords));
    }
}
