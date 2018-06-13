import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  // 100/100
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p202Earthquake3 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        ArrayDeque<List<Integer>> allNumbers = new ArrayDeque<>();
        List<Integer> output = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            String[] line = reader.readLine().split(" ");
            List<Integer> tempList = new ArrayList<>();
            for (String aLine : line) {
                tempList.add(Integer.valueOf(aLine));
            }
            allNumbers.offer(tempList);
        }

        while (!allNumbers.isEmpty()) {
            List<Integer> currentNumbers = allNumbers.pop();
            int wave = currentNumbers.get(0);
            for (int i = 1; i < currentNumbers.size(); i++) {
                if (wave < currentNumbers.get(i)) {
                    allNumbers.addLast(currentNumbers.subList(i, currentNumbers.size()));
                    break;
                }
            }
            output.add(wave);
        }

        System.out.println(output.size());
        System.out.println(Arrays.toString(output.toArray()).replaceAll("[\\]\\[,]", ""));
    }
}