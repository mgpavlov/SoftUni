import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;   // 100/100

public class _02_Hot_Potato {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");

        ArrayDeque<String> queue = new ArrayDeque<>();

        int n = Integer.parseInt(scan.nextLine());
        queue.addAll(Arrays.asList(input));

        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                queue.offer(queue.poll());
            }
            System.out.println("Removed " + queue.poll());
        }
        System.out.println("Last is " + queue.poll());
    }
}