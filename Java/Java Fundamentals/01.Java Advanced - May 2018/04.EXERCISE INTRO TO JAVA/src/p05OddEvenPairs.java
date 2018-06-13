import java.util.Scanner;

public class p05OddEvenPairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArray = input.split("\\s");
        if (inputArray.length%2 != 0){
            System.out.println("invalid length");
            return;
        }

        for (int i = 0; i < inputArray.length; i+=2) {
            String evenOrOdd = "different";
            int a = Integer.parseInt(inputArray[i]);
            int b = Integer.parseInt(inputArray[i+1]);
            if (a%2==0 && b%2==0){
                evenOrOdd = "both are even";
            }
            if (a%2!=0 && b%2!=0){
                evenOrOdd = "both are odd";
            }

            System.out.printf("%d, %d -> %s", a, b, evenOrOdd);
            System.out.println();
        }
    }
}
