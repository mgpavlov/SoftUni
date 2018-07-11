import java.util.Scanner;

public class GameOfNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        int maxScore = Integer.MAX_VALUE;
        int sumAscii = 0;
        String winnter = "";
        for (int i = 0; i <number; i++) {
            String name = scanner.nextLine();
            int score = Integer.parseInt(scanner.nextLine());
            sumAscii+=score;
            char[] splitName = name.toCharArray();
            for (char c : splitName) {
                if((int)c % 2 == 0) {
                    sumAscii += (int) c;
                }
                else {
                    sumAscii-=(int)c;
                }
            }
            if(sumAscii > maxScore){
                maxScore = sumAscii;
                winnter = name;
            }
            sumAscii = 0;
        }
        System.out.printf("The winner is %s - %d points" ,winnter , maxScore);
    }
}