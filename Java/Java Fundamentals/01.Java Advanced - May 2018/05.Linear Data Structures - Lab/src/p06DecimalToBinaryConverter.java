import java.util.ArrayDeque;
import java.util.Scanner;  //80/100 judge

public class p06DecimalToBinaryConverter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        ArrayDeque<Integer> binary = new ArrayDeque<Integer>();

        while (number != 0){
            binary.push(number%2);
            number /=2;
        }
        while(binary.size()>0){
            System.out.print(binary.pop());
        }

    }
}
