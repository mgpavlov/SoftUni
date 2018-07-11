import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class _01_BitShow {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] x = scan.nextLine().split("\\s+");
        Deque<Integer> stack = new ArrayDeque<>();

        for (String eachNumber : x) {

            stack.add(Integer.parseInt(eachNumber));
        }
        Integer counterGolds = 0;
        Integer counter = x.length;

        while (true) {
            String current = scan.nextLine();
            if ("Revision".equals(current)) {
                break;
            }
            String[] as = current.split("\\s+");

            if (as[0].equals("Apply") && as[1].equals("acid")) {

                if (stack.size() >= 1) {
                    Integer powerAsid = Integer.parseInt(as[2]);
                    for (int i = 0; i < powerAsid; i++) {

                        if (stack.size() > 0) {
                            Integer check = stack.peek();
                            stack.removeFirst();
                            if (check == 1) {  //  when first equals 1
                                counterGolds++;
                            }
                            else if (check > 1 && stack.size() >= 1) {
                                stack.addLast(check - 1);
                            }
                        }
                    }
                }

            } else if (as[0].equals("Air") && as[1].equals("leak")) {

                Integer powerAsid = Integer.parseInt(as[2]);

                if (counterGolds >= 1) {
                    stack.addLast(powerAsid);
                    counterGolds--;
                }
            }
        }

        printtt(stack, counter);
    }

    private static void printtt(Deque<Integer> stack, int counter) {
        String formattedString = stack.toString()
                .replace(",", "")  //remove the commas
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim();
        System.out.println(formattedString);
        System.out.println(counter - stack.size());
    }
}
