import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p08HotPotato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayDeque <String> names = new ArrayDeque<>();

        Collections.addAll(names, sc.nextLine().split(" "));
        int n = Integer.parseInt(sc.nextLine());

        while (names.size()>1){
            for (int i = 1; i < n; i++) {
                names.offer(names.poll());
            }
            System.out.println("Removed "+names.poll());
        }

        System.out.println("Last is "+names.poll());
    }
}
