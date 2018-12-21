package p01ListIterator_p02Collection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ListyIteratorImpl<String> li = null;
        while (true) {
            String[] command = reader.readLine().split("\\s+");
            if (command[0].equals("END")) {
                break;
            }
            switch (command[0]) {
                case "Create":
                    li = new ListyIteratorImpl<>(Arrays.copyOfRange(command, 1, command.length));
                    break;
                case "Move":
                    System.out.println(li.move());
                    break;
                case "HasNext":
                    System.out.println(li.hasNext());
                    break;
                case "Print":
                    try {
                        li.print();
                    } catch (UnsupportedOperationException ex) {
                        System.out.println(ex.getMessage());
                    }

                    break;
                case "PrintAll":
                    li.printAll();
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }
        }

    }
}
