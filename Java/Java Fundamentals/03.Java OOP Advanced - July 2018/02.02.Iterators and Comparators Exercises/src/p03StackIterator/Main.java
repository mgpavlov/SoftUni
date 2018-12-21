package p03StackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<String> stack = new Stack<>();

        String command = reader.readLine();
        while (!"END".equals(command)) {
            if (command.equals("Pop")) {
                try {
                    stack.pop();
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("Push")) {
                String[] elements = command.substring(5).split(", ");
                for (String element : elements) {
                    stack.push(element);
                }
            }

            command = reader.readLine();
        }
        int n = 2;
        while (n-- > 0)
            for (String s : stack) {
                System.out.println(s);
            }
    }
}
