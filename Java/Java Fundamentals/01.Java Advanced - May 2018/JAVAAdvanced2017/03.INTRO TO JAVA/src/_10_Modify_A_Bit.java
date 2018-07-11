import java.util.Scanner;

public class _10_Modify_A_Bit {              // 100/100
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int p = input.nextInt();
        int v = input.nextInt();

        if (v == 0)
        {
            int mask = ~(1 << p);
            int result = n & mask;
            System.out.println(result);
        }
        else
        {
            int mask = 1 << p;
            int result = n | mask;
            System.out.println(result);
        }
    }
}

