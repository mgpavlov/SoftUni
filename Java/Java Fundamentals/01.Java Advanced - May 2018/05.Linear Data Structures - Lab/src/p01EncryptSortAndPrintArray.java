import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class p01EncryptSortAndPrintArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] words = new String[n];
        for (int i = 0; i < n; i++) {

            String word = sc.nextLine();
            words[i] = word;
        }
        int[] encriptedArray = new int[n];
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < words[i].length(); j++) {

                if (isVowel(words[i].charAt(j))){
                    sum += words[i].charAt(j)*words[i].length();
                }else {
                    sum += words[i].charAt(j)/words[i].length();
                }
            }
            encriptedArray[i] = sum;
        }

        Arrays.sort(encriptedArray);
        for (int i : encriptedArray) {
            System.out.println(i);
        }
    }
    public static boolean isVowel (char letter){
        char [] vowels = new char[]{'a', 'o', 'e', 'i', 'u'};
        for (char vowel : vowels) {
            if (vowel == Character.toLowerCase(letter)){
                return true;
            }
        }
        return false;
    }

}
/*
import java.util.*;  // 100/100

public class _02_Encrypt_Sort_and_Print_Array {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        String[] lines = new String[n];
        for (int i = 0; i <= n - 1; i++) {
            lines[i] = scanner.nextLine();
        }

        int[] encryptedArray = encryptArray(lines);
        Arrays.sort(encryptedArray);

        printArray(encryptedArray);
    }

    private static int[] encryptArray(String[] lines) {
        int[] encryptedArray = new int[lines.length];
        List<Character> vowels = new ArrayList<>();
        Collections.addAll(vowels, 'a', 'e', 'i', 'o', 'u','A','E','I','O','U');
        for (int i = 0; i < lines.length; i++) {
            int sum = 0;

            for (int j = 0; j < lines[i].length(); j++) {
                if (vowels.contains(lines[i].charAt(j))) {
                    sum += (int) (lines[i].charAt(j)) * lines[i].length();
                } else {
                    sum += (int) (lines[i].charAt(j)) / lines[i].length();
                }
            }
            encryptedArray[i] = sum;
        }
        return encryptedArray;
    }

    private static void printArray(int[] lines) {
        for (int line : lines) {
            System.out.println(line);
        }
    }
}*/
