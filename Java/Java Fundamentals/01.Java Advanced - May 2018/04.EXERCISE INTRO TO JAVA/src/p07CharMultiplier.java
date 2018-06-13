import java.util.Scanner;

public class p07CharMultiplier {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String[] input = sc.nextLine().split("\\s");
        String input1 = input[0];
        String input2 = input[1];
        int diff = Math.max(input1.length(), input2.length()) - Math.min(input1.length(), input2.length());

        int sum = 0;

        for (int i = 0; i < Math.min(input1.length(), input2.length()); i++) {
            int char1 = input1.toCharArray()[i];
            int char2 = input2.toCharArray()[i];
            sum += char1*char2;
        }

        if (diff!= 0){
            String longer = input1.length()>input2.length()? input1: input2;
            String shorter = input1.length()<input2.length()? input1: input2;

            for (int i = shorter.length(); i < longer.length(); i++) {
                sum+= (int) longer.toCharArray()[i];
            }
        }

        System.out.println(sum);
    }
}
