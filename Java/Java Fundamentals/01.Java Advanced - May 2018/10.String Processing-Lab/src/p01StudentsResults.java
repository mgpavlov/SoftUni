import java.text.DecimalFormat;
import java.util.Scanner;

public class p01StudentsResults {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("00000#.##");

        System.out.println(df.format(125.50)); // 5.5  изрязва ненужните нулите
        System.out.println(df.format(5.55)); // 5.55
        System.out.println(df.format(5.05)); //5.05
        System.out.println(df.format(5.05589)); //5.06 закръглява нагоре

        DecimalFormat df2 = new DecimalFormat("0.000");

        System.out.println(df2.format(5.5)); // 5.500  добавя нули
        System.out.println(df2.format(5.55)); // 5.550
        System.out.println(df2.format(5.05)); //5.050
        System.out.println(df2.format(5.05589)); //5.056 закръглява нагоре

        DecimalFormat df3 = new DecimalFormat("0000.000");

        System.out.println(df3.format(5.5)); // 0005.500  добавя нули отпред
        System.out.println(df3.format(5.55)); // 0005.550
        System.out.println(df3.format(5.05)); //0005.050
        System.out.println(df3.format(5.05589)); //0005.056 закръглява нагоре

    }
}
