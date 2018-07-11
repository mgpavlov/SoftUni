import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class test {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Consumer<Integer> add = x -> x = x + 1;
        Consumer<Integer> multiply = x -> x = x * 2;
        Consumer<Integer> subtract = x -> x = x - 1;
        Consumer<Integer> print = x -> System.out.print(x + " ");

        String operations = scanner.nextLine();

        while (!operations.equals("end")) {

            switch (operations) {

                case "add":
                    Arrays.stream(numbers).forEach(add::accept);
                    break;
                case "multiply":
                    Arrays.stream(numbers).forEach(multiply::accept);
                    break;
                case "subtract":
                    Arrays.stream(numbers).forEach(subtract::accept);
                    break;
                case "print":
                    Arrays.stream(numbers).forEach(print::accept);
                    break;
            }
            operations = scanner.nextLine();
        }
    }
}