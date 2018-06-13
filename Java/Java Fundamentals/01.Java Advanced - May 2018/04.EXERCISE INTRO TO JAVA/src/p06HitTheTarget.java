import java.util.Scanner;

public class p06HitTheTarget {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i+j == number){
                    System.out.printf("%d + %d = %d", i, j, number);
                    System.out.println();
                }
                if (i-j == number){
                    System.out.printf("%d - %d = %d", i, j, number);
                    System.out.println();
                }
            }
        }
    }
}
