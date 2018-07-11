import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;  // 100/100

public class _01_Sort_Even_Numbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split(", ");
        List<Integer> numbers = new ArrayList<>();
        for (String s : input) {
            numbers.add(Integer.valueOf(s));
        }
        numbers.removeIf(n -> n%2 != 0);
        System.out.println(Arrays.toString(numbers.toArray()).replace("[","").replace("]",""));
        numbers.sort(Integer::compareTo);
        System.out.println(numbers.toString().substring(1, numbers.toString().length() - 1));
    }
}
