import java.math.BigInteger;
import java.util.Scanner;

public class p07ProductOfNumbersNtoM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int temp = n;
        BigInteger sum = BigInteger.ONE;
        do {
            sum = sum.multiply(BigInteger.valueOf(temp));
            temp++;
        }while (temp<=m);

        System.out.printf("product[%d..%d] = %d",n, m, sum);
    }
}
