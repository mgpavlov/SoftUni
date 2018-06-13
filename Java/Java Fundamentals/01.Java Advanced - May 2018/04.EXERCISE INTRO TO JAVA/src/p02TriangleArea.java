import java.util.Scanner;

public class p02TriangleArea {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x1 = sc.nextInt();
        double y1 = sc.nextInt();
        double x2 = sc.nextInt();
        double y2 = sc.nextInt();
        double x3 = sc.nextInt();
        double y3 = sc.nextInt();


        double area = Math.abs((x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2);

        System.out.printf("%d",(int)area);
    }
}
