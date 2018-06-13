import java.util.Scanner;

public class p10ModifyABit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int p = sc.nextInt();
        int v = sc.nextInt();

        if (v == 0){
            int mask = ~(1 << p);
            int result = n & mask;
            System.out.println(result);
        }else {
            int mask = 1 << p;
            int result = n | mask;
            System.out.println(result);
        }
    }
}
