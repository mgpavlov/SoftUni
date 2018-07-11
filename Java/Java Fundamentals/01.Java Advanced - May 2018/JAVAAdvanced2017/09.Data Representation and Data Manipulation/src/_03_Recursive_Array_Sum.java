import java.util.Scanner;  // 100/100

public class _03_Recursive_Array_Sum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] area = scan.nextLine().split("\\s+");
        Integer sum = 0;

        for (String anArea : area) {
            sum += Integer.parseInt(anArea);
        }
        System.out.println(sum);


    }
}
