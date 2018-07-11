import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.util.ArrayDeque;
import java.util.Deque;

public class _09_The_Stock_Span_Problem {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.valueOf(bf.readLine());
        int[] prices = new int[count];
        int[] spans = new int[count];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < prices.length; i++) {
            prices[i] = Integer.valueOf(bf.readLine());
        }

        stack.push(0);
        spans[0] = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(spans[0]).append("\n");

        for (int i = 1; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                spans[i] = i + 1;
            } else {
                spans[i] = i - stack.peek();
            }
            sb.append(spans[i]).append("\n");
            stack.push(i);
        }

        System.out.print(sb.toString());
    }
}
