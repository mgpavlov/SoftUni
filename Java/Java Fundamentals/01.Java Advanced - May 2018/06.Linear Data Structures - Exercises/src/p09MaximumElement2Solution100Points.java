import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class p09MaximumElement2Solution100Points {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        ArrayDeque<Integer> maxStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String[] inputs = reader.readLine().split(" ");
            String command = inputs[0];
            switch (command) {
                case "1":
                    int elem = Integer.parseInt(inputs[1]);
                    stack.push(elem);
                    if (maxStack.isEmpty() || elem > maxStack.peek()) {
                        maxStack.push(elem);
                    }
                    break;
                case "2":
                    if(stack.pop().equals(maxStack.peek())){
                        maxStack.pop();
                    }
                    break;
                case "3":
                    sb.append(maxStack.peek()).append(System.lineSeparator());
                    break;
            }
        }
        System.out.print(sb);
    }
}
