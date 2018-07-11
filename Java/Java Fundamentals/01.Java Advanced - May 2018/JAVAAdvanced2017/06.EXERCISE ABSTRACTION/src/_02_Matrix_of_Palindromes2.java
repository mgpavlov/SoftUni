import java.util.Scanner;

public class _02_Matrix_of_Palindromes2 {    // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] input = scan.nextLine().split(" ");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        char one = 'a';
        char two = 'a';

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                System.out.print(one + "" + two + "" + one + " ");
                two++;
            }
            System.out.println();
            one++;
            two = one;

        }
    }
}
