package src;

import java.math.BigInteger;   // 100/100
import java.util.Scanner;

public class _02_Sum_Big_Numbers {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String first = in.nextLine();
        String second = in.nextLine();

        BigInteger result = new BigInteger(first).add(new BigInteger(second));
        System.out.println(result);
    }
}
