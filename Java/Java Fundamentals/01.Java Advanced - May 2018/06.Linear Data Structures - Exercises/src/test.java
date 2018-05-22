import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Deque<String> products = new ArrayDeque<>();

        String sequence = sc.nextLine();

        if (sequence.length()%2 != 0) System.out.println("NO");

        for (int i = 0; i < sequence.length()/2; i++) {
            if (sequence.charAt(sequence.length()/2 - i) == ' '){
                System.out.println("NO");
                return;
            }

        }
    }
}