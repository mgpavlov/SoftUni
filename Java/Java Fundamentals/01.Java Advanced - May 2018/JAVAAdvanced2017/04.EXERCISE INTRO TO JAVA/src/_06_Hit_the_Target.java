import java.util.Scanner;

public class _06_Hit_the_Target {              // 100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Integer n = scanner.nextInt();

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                if (i + j == n)
                {
                    System.out.printf("%d + %d = %d\n",i,j,n);
                }
                if (i - j == n)
                {
                    System.out.printf("%d - %d = %d\n",i,j,n);
                }
            }
        }

    }
}
