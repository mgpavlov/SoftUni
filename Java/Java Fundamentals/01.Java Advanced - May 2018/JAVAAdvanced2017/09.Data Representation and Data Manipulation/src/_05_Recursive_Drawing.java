import java.util.Collections;
import java.util.Scanner;

public class _05_Recursive_Drawing {           // 100/100
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Integer n = Integer.parseInt(scan.nextLine());

        for (int i = n; i > 0; i--) {
            System.out.println(String.join("", Collections.nCopies(i, "*")));
        }

        for (int i = 1; i <= n; i++) {
            System.out.println(String.join("", Collections.nCopies(i, "#")));
        }


    }
}
