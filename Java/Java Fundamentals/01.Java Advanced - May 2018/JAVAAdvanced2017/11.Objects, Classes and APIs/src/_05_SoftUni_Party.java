import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;   // 60/100

public class _05_SoftUni_Party {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Set<String> VIP = new LinkedHashSet<>();
        Set<String> normal = new LinkedHashSet<>();

        while (true) {
            String current = scan.nextLine();
            if (current.equals("PARTY")) {
                break;
            }

            if (current.length() == 8 && Character.isLetter(current.charAt(0))) {
                VIP.add(current);
            } else if (current.length() == 8) {
                normal.add(current);
                if (VIP.contains(current)){
                    VIP.remove(current);
                }
            }
        }
        while (true) {
            String current = scan.nextLine();
            if (current.equals("END")) {
                break;
            }
            if (VIP.contains(current)) {
                VIP.remove(current);
            }
            if (normal.contains(current)) {
                normal.remove(current);
            }

        }
        int notComeGuest = VIP.size() + normal.size();
        System.out.printf("%d%n", notComeGuest);
        for (int i = 0; i < notComeGuest; i++) {

            for (String aNormal : normal) {
                System.out.println(aNormal);
            }
            for (String aVIP : VIP) {
                System.out.println(aVIP);
            }
            break;
        }
    }
}
