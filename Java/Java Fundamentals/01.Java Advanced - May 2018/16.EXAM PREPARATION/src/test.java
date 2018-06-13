import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Deque<Integer> input = Arrays.stream(reader.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        input.offer(12213223);
        input.add(333);
        input.push(0);
        System.out.println(input);
        System.out.println(input.remove());
        System.out.println(input.poll());
        System.out.println(input.pop());
        System.out.println(input.removeFirst());
        System.out.println(input.removeLast());
        System.out.println(input);
        System.out.println(input.peek());
        System.out.println(input.peekFirst());
        System.out.println(input.peekLast());
        System.out.println(input);
    }
}

//1 2 3 4 5 6 7 8 9