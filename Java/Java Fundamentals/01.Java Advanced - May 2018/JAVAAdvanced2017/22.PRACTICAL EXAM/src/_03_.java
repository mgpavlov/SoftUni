import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class _03_ {
    public static void main(String[] args) throws IOException {
        System.out.println("The V-Logger has a total of 0 vloggers in its logs.");


        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, TreeMap<Integer, Integer>> allDragons = new LinkedHashMap<>();

        while (true) {
            String x = bf.readLine();

            if (x.equals("Statistics")) {
                break;
            }
            String[] comands = x.split("\\s+");

            if (!allDragons.containsKey(comands[0]))
            {
                if (comands[1].equals("joined") && comands[2].equals("The") && comands[3].equals("V-Logger")) {
                    allDragons.put(comands[0], new TreeMap<>());
                }
            }




        }

    }
}
