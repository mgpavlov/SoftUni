import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.io.BufferedReader; // 100/100

public class _05_SoftUni_Party2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Set<String> reservationList = new TreeSet<>();
        Set<String> vipGuests = new LinkedHashSet<>();
        Set<String> regularGuests = new TreeSet<>();

        while (true) {
            String input = bf.readLine();

            if ("PARTY".equalsIgnoreCase(input)) {
                break;
            }

            reservationList.add(input);
        }

        while (true) {
            String input = bf.readLine();

            if ("END".equalsIgnoreCase(input)) {
                break;
            }

            if (reservationList.contains(input)) {
                reservationList.remove(input);
            }
        }

        System.out.println(reservationList.size());

        for (String guest : reservationList) {
            boolean isVip = Character.isDigit(guest.charAt(0));

            if (isVip) {
                vipGuests.add(guest);
            } else {
                regularGuests.add(guest);
            }
        }

        for (String vipGuest : vipGuests) {
            System.out.println(vipGuest);
        }

        for (String regularGuest : regularGuests) {
            System.out.println(regularGuest);
        }
    }
}