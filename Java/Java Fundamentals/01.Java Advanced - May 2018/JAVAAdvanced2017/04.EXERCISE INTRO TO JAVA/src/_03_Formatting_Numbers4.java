import java.util.Scanner;

public class _03_Formatting_Numbers4 {          // 100/100
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer a = sc.nextInt();
        double b = sc.nextDouble();
        double c = sc.nextDouble();

        String s = Integer.toHexString(a);
        System.out.printf("|%-10s",s.toUpperCase());
        System.out.printf("|%010d|",Integer.parseInt(Integer.toBinaryString(a)));
        System.out.printf("%10.2f",b);
        System.out.printf("|%-10.3f|",c);
    }
}
