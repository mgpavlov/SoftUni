import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p09MathPotato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayDeque<String> names = new ArrayDeque<>();
        Collections.addAll(names, sc.nextLine().split(" "));

        int n = Integer.parseInt(sc.nextLine());
        int cycle = 1;
        while (names.size() > 1) {
            for (int i = 1; i < n; i++) {
                names.offer(names.poll());
            }
            if (IsPrime(cycle)) {
                System.out.println("Prime " + names.peek());
            } else {
                System.out.println("Removed " + names.poll());
            }
            cycle++;
        }
        String lastName = names.poll();
        System.out.println("Last is " + lastName);
    }

    private static boolean IsPrime(int n) {
        boolean primeCheck = true;
        if (n == 0 || n == 1) {
            primeCheck = false;
            return primeCheck;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    primeCheck = false;
                }
            }
            return primeCheck;
        }
    }

}
