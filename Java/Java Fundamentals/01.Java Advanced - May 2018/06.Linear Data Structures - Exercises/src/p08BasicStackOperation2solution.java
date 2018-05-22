import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;

public class p08BasicStackOperation2solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] commands = scan.nextLine().split("\\s+");
        String[] secondLine = scan.nextLine().split("\\s+");

        Integer countRemoveElements = Integer.parseInt(commands[1]);
        Integer searchedElement = Integer.parseInt(commands[2]);

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Arrays.stream(secondLine).map(Integer::parseInt).forEach(stack::push);
        IntStream.range(0, countRemoveElements).forEach(i -> stack.removeFirst());
        if (stack.contains(searchedElement)) {
            System.out.println("true");
        } else {
            if (stack.size() == 0) {
                System.out.print(stack.size());

            } else {
                System.out.println(Collections.min(stack));
            }
        }
    }
}
