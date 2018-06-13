import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class p02SoftUniParty {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        Set<String> guestList = new TreeSet<>();
        while (!line.equals("PARTY")){
            guestList.add(line);
            line = sc.nextLine();
        }

        line = sc.nextLine();
        while (!line.equals("END")){
            guestList.remove(line);
            line = sc.nextLine();
        }

        System.out.println(guestList.size());
        System.out.println(String.join("\n", guestList));
    }
}
