import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p07ReverseNumbersWithStack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (String anInput : input) {
            stack.push(Integer.parseInt(anInput));
        }

        for (Integer integer : stack) {
            System.out.print(integer + " ");
        }

    }
}
