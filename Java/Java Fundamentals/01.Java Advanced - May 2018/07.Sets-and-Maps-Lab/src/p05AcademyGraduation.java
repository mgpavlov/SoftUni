import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class p05AcademyGraduation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, double[]> students = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            String name = sc.nextLine();
            double[] ratings = Arrays.stream(sc.nextLine().split(" ")).mapToDouble(Double::parseDouble).toArray();

            students.put(name, ratings);
        }

        for (Map.Entry<String, double[]> student : students.entrySet()) {
            double averageRating = 0;
            for (double rating : student.getValue()) {
                averageRating+=rating;
            }
            averageRating /= student.getValue().length;
            System.out.printf("%s is graduated with %s%n", student.getKey(),averageRating);
        }
    }
}
