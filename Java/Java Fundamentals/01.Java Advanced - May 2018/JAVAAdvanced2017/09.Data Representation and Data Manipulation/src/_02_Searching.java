import java.util.Scanner;

public class _02_Searching {  // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] area = scan.nextLine().split("\\s+");
        Integer searched = Integer.parseInt(scan.nextLine());

        boolean isFound = true;

        for (int i = 0; i < area.length; i++) {
            int current = Integer.parseInt(area[i]);

            if (current == searched) {
                System.out.println(i);
                isFound = false;
                break;
            }
        }
        if (isFound){
            System.out.println(-1);
        }

    }
}
