import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class p01UniqueUsernames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set <String> names = new LinkedHashSet<>();
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            names.add(name);
        }
        System.out.println(String.join("\n", names));
    }
}
