import java.util.Scanner;

public class p09ExtractABitFromInteger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();

        int mask = n >> p;
        int result = 1 & mask;
        System.out.println(result);
    }
}
