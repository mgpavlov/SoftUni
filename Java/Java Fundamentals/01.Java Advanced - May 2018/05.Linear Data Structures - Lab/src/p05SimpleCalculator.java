import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p05SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split("\\s+");

        ArrayDeque <String> stack = new ArrayDeque<>();
        Collections.addAll(stack, input);

        while (stack.size()>1){
            int firstNum = Integer.parseInt(stack.pop());
            String operant =stack.pop();
            int secondNum = Integer.parseInt(stack.pop());
            int tempNum = 0;
            if ("+".equals(operant)){
                tempNum = firstNum+secondNum;
            }else if ("-".equals(operant)){
                tempNum = firstNum - secondNum;
            }

            stack.push(String.valueOf(tempNum));
        }

        System.out.println(stack.peek());
    }
}
