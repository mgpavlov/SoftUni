import java.util.ArrayDeque;
        import java.util.Scanner;

public class p07MatchingBrackets {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '('){
                stack.push(i);
            }else if (input.charAt(i) == ')'){
                int startIndex = stack.pop();
                String contents = input.substring(startIndex, i+1);
                System.out.println(contents);
            }
        }
    }
}
