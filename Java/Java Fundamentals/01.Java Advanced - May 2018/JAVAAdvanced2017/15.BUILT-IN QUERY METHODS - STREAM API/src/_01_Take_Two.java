import java.util.*;
import java.util.Scanner;  // 100/100

public class _01_Take_Two {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> tokens = Arrays.asList(scan.nextLine().split("\\s+"));
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            numbers.add(Integer.valueOf(token));
        }
        numbers.stream().filter(n -> 10 <= n && n <= 20).distinct().limit(2).forEach(n -> System.out.print(n + " "));

    }
}
