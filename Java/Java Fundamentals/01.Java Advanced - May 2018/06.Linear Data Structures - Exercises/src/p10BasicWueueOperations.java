import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class p10BasicWueueOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] firstLine = sc.nextLine().split(" ");
        String [] secondLine = sc.nextLine().split(" ");

        int elementsToAdd = Integer.parseInt(firstLine[0]);
        int elementsToRemove = Integer.parseInt(firstLine[1]);
        int elementToSearch = Integer.parseInt(firstLine[2]);

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < elementsToAdd; i++) {
            deque.add(Integer.parseInt(secondLine[i]));
        }
        for (int i = 0; i < elementsToRemove; i++) {
            if (deque.isEmpty()) break;
            deque.poll();
        }

        if (deque.isEmpty()){
            System.out.println(0);
        }else if (deque.contains(elementToSearch)){
            System.out.println(true);
        }else{
            System.out.println(Collections.min(deque));
        }
    }
}
