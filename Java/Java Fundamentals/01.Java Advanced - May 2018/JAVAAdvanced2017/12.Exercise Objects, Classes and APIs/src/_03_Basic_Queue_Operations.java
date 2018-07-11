import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

public class _03_Basic_Queue_Operations {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split("\\s+");

        int addElements = Integer.parseInt(input[0]);
        int removeElements = Integer.parseInt(input[1]);
        int searchedInteger = Integer.parseInt(input[2]);

        String[] area = reader.readLine().split("\\s+");

        Deque<Integer> stack = new ArrayDeque<>();


        for (int i = 0; i < addElements; i++) {
            stack.push(Integer.parseInt(area[i]));
        }
        for (int i = 0; i < removeElements; i++) {
            stack.removeLast();
        }

        if (stack.contains(searchedInteger)) {
            System.out.printf("true");
        } else {
            if (stack.size() != 0) {
                System.out.println(Collections.min(stack));
            }
            else {
                System.out.printf("0");
            }
        }
    }
}
