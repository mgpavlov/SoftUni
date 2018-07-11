import java.util.Scanner;

public class _12_To_the_Stars {   // 100/100
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String[] firstStar = scan.nextLine().split(" ");
        String firstStarName = firstStar[0].toLowerCase();
        Double fistStarX = Double.parseDouble(firstStar[1]);
        Double fistStarY = Double.parseDouble(firstStar[2]);

        String[] secondStar = scan.nextLine().split(" ");
        String secoundStarName = secondStar[0].toLowerCase();
        Double secoundStarX = Double.parseDouble(secondStar[1]);
        Double secoundStarY = Double.parseDouble(secondStar[2]);

        String[] thirdStar = scan.nextLine().split(" ");
        String thirdStarName = thirdStar[0].toLowerCase();
        Double thirdStarX = Double.parseDouble(thirdStar[1]);
        Double thirdStarY = Double.parseDouble(thirdStar[2]);

        String[] startCordinatio = scan.nextLine().split(" ");
        Double startX = Double.parseDouble(startCordinatio[0]);
        Double startY = Double.parseDouble(startCordinatio[1]);

        Integer rotations = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <= rotations; i++) {

            if (fistStarX + 1 >= startX && startX >= fistStarX - 1 &&
                    fistStarY + 1 >= startY && startY >= fistStarY - 1)
            {
                System.out.println(firstStarName);
            }
            else if (secoundStarX + 1 >= startX && startX >= secoundStarX - 1 &&
                    secoundStarY + 1 >= startY && startY >= secoundStarY - 1)
            {
                System.out.println(secoundStarName);
            }
            else if (thirdStarX + 1 >= startX && startX >= thirdStarX - 1 &&
                    thirdStarY + 1 >= startY && startY >= thirdStarY - 1)
            {
                System.out.println(thirdStarName);
            }
            else
            {
                System.out.println("space");
            }
            startY += 1;
        }
    }
}