import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;  // 100/100

public class _05_Balanced_Parentheses {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Deque<Character> dq = new ArrayDeque<>();

        String input = scan.nextLine();
        boolean isFound = false;

        for (int i = 0; i < input.length(); i++) {

            Character curentChar = input.charAt(i);
            if (curentChar.equals('{')) {
                dq.push(curentChar);
            } else if (curentChar.equals('[')) {
                dq.push(curentChar);
            } else if (curentChar.equals('(')) {
                dq.push(curentChar);
            } else if (curentChar.equals('}')) {
                try {
                    Character lastChar = dq.peekFirst();
                    if (!lastChar.equals('{')) {
                        isFound = true;
                        break;
                    } else {
                        dq.pop();
                    }
                }
                catch (NullPointerException e) {
                    System.out.printf("NO");
                    return;
                }
            } else if (curentChar.equals(']')) {
                try {
                    Character lastChar = dq.peekFirst();
                    if (!lastChar.equals('[')) {
                        isFound = true;
                        break;
                    } else {
                        dq.pop();
                    }
                }
                catch (NullPointerException e) {
                    System.out.printf("NO");
                    return;
                }
            } else if (curentChar.equals(')')) {
                try {
                    Character lastChar = dq.peekFirst();
                    if (!lastChar.equals('(')) {
                        isFound = true;
                        break;
                    } else {
                        dq.pop();
                    }
                } catch (NullPointerException e) {
                    System.out.printf("NO");
                    return;
                }
            }
        }

        if (input.length() == 1) {
            System.out.printf("NO");
        } else {
            if (!isFound) {
                System.out.printf("YES");
            } else {
                System.out.printf("NO");
            }
        }
    }
}