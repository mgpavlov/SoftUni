import java.util.Scanner;     // 100/100

public class _09_Extract_a_Bit_from_Integer2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int p = input.nextInt();

        int nRightP = n >> p;
        int bit = nRightP & 1;

        System.out.println(bit);
    }
}
