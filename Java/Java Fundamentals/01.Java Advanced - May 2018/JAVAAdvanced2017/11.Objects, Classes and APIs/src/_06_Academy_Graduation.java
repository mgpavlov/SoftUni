import java.util.*;

public class _06_Academy_Graduation {  // 100/100
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Map<String, Double> mapche = new LinkedHashMap<>();

        Integer n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            String name = scan.nextLine();
            String[] grades = scan.nextLine().split(" ");
            Double average = 0.0;

            for (String grade : grades) {
                average += Double.parseDouble(grade);
            }
            average = average / grades.length;

            mapche.put(name, average);
        }
        for (int i = 0; i < mapche.size(); i++) {
            List<String> keys = new ArrayList<>(mapche.keySet());
            List<Double> values = new ArrayList<>(mapche.values());

            String curentKey = keys.get(i);
            String curentValue = values.get(i).toString();

            System.out.printf("%s is graduated with %s%n", curentKey, curentValue);
        }
    }
}
