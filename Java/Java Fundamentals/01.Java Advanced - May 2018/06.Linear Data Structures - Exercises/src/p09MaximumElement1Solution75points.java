import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p09MaximumElement1Solution75points { //75/100 judge
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int commands = Integer.parseInt(sc.nextLine());

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < commands; i++) {
            String command = sc.nextLine();
            if (command.startsWith("1")){
                int element = Integer.parseInt((command.split(" ")[1]));
                stack.push(element);
            }else if ("2".equals(command)){
                stack.pop();
            }else if ("3".equals(command)){
                System.out.println(Collections.max(stack));
            }
        }
    }
}
