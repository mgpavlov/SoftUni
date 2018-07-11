import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;  // 20/100

public class _03_Math_Potato {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");

        ArrayDeque<String> queue = new ArrayDeque<>();

        int n = Integer.parseInt(scan.nextLine());
        queue.addAll(Arrays.asList(input));

        int counter = 0;
        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                queue.offer(queue.poll());
                counter++;
            }
            if (isPrime(counter)) {
                System.out.println("Removed " + queue.poll());
            } else {
                System.out.println("Prime " +queue.peek());
            }
        }
        System.out.println("Last is " + queue.poll());
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}
