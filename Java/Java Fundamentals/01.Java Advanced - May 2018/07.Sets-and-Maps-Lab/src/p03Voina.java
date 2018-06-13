import java.util.*;

public class p03Voina {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] firstPlayer = sc.nextLine().split(" ");
        String[] secondPlayer = sc.nextLine().split(" ");

        Set<String> firstPlayerCards = new LinkedHashSet<>(Arrays.asList(firstPlayer));
        Set<String> secondPlayerCards = new LinkedHashSet<>(Arrays.asList(secondPlayer));

        for (int i = 0; i < 50; i++) {
            if (firstPlayerCards.isEmpty()) {System.out.println("Second player win!"); break;}
            if (secondPlayerCards.isEmpty()) {System.out.println("First player win!"); break;}
            String topFirst = firstPlayerCards.iterator().next();
            firstPlayerCards.remove(topFirst);
            String topSecond = secondPlayerCards.iterator().next();
            secondPlayerCards.remove(topSecond);
            if (Integer.parseInt(topFirst)>Integer.parseInt(topSecond)){
                firstPlayerCards.add(topFirst);
                firstPlayerCards.add(topSecond);
            }else if (Integer.parseInt(topFirst)<Integer.parseInt(topSecond)){
                secondPlayerCards.add(topFirst);
                secondPlayerCards.add(topSecond);
            }
        }

        if (firstPlayerCards.size() > secondPlayerCards.size()){
            System.out.println("First player win!");
        }else if (firstPlayerCards.size() < secondPlayerCards.size()){
            System.out.println("Second player win!");
        }else {
            System.out.println("Draw");
        }
    }
}
