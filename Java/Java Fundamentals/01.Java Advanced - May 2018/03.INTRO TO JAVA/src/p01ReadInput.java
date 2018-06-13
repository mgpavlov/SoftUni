import java.util.Scanner;

public class p01ReadInput {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String firstWord = sc.next("\\w+");
        String secondWord = sc.next("\\w+");
        int firstNum = sc.nextInt();
        double secNum = sc.nextDouble();
        double thirdNum = sc.nextDouble();

        double sum = firstNum + secNum + thirdNum;
        sc.nextLine();
        String thirdWord= sc.nextLine();
        System.out.println(firstWord +" " + secondWord +" " + thirdWord + " " + (int)sum);
    }
}
