import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;  // 100/100

public class _01_Matching_Brackets {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        //String s = "1 + (2 - (2 + 3) * 4 / (3 + 1)) * 5";
        String s = scan.nextLine();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int index = 0; index < s.length(); index++) {
            char ch = s.charAt(index);
            if (ch == '(') {
                stack.push(index);
            } else if (ch == ')') {
                System.out.println(s.substring(stack.pop(), index + 1));
            }
        }
    }
}
