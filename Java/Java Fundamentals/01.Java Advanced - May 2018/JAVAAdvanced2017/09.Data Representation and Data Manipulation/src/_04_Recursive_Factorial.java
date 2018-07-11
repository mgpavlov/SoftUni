import java.util.*;

public class _04_Recursive_Factorial {   // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Integer n = Integer.parseInt(scan.nextLine());

        Integer sum = 1;

        for (int i = 1; i <= n; i++) {
            sum *= i;
        }
        System.out.println(sum);


    }
}
