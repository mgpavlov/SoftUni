import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;  // 100/100
import java.util.Scanner;
import java.util.stream.IntStream;

public class _01_Basic_Stack_Operations {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split("\\s+");
        String[] area = scan.nextLine().split("\\s+");

        Integer countRemoveElements = Integer.parseInt(input[1]);
        Integer searchedElement = Integer.parseInt(input[2]);

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Arrays.stream(area).map(Integer::parseInt).forEach(deque::push);
        IntStream.range(0, countRemoveElements).forEach(i -> deque.removeFirst());
        if (deque.contains(searchedElement)) {
            System.out.println("true");
        } else {
            if (deque.size() == 0) {
                System.out.print(deque.size());

            } else {
                System.out.println(Collections.min(deque));
            }
        }
    }
}
