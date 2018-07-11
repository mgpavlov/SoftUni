import java.math.BigInteger;
import java.util.Scanner;

public class _07_Product_of_Numbers_N_M {  //100/100
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer first = Integer.parseInt(scanner.next());
        Integer secound = Integer.parseInt(scanner.next());

        BigInteger sum = new BigInteger("1");
        for (int i = first; i <= secound; i++) {
            BigInteger numberBig = new BigInteger("" + i);

            sum = sum.multiply(numberBig);
        }
        System.out.printf("product[%d..%d] = %d\n", first, secound, sum);
    }
}
