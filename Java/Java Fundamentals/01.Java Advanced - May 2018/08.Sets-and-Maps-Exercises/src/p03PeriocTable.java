import java.util.*;

import static java.util.Collections.*;

public class p03PeriocTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Set<String> elements = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            elements.addAll(Arrays.asList(line));
        }

        String[] arrayElements = elements.toArray(new String[elements.size()]);
        for (int i = 0; i < arrayElements.length; i++) {
            System.out.print(arrayElements[i]+" ");
        }
    }
}
