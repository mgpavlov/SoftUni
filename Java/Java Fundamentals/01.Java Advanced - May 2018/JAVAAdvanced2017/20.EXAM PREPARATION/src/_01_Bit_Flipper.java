import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; // 100/100
import java.math.BigInteger;

public class _01_Bit_Flipper {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger num = new BigInteger(reader.readLine());
        BigInteger seven = new BigInteger("7");
        BigInteger zero = new BigInteger("0");

        for (int i = 0; i <= 61; i++) {
            BigInteger threeDigits = num.shiftRight(61 - i).and(seven);
            if (threeDigits.equals(seven) || threeDigits.equals(zero)) {
                num = num.xor(seven.shiftLeft(61 - i));
                i += 2;
            }
        }
        System.out.println(num);
    }
}
