import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;  //  100/100
import java.util.LinkedHashMap;
import java.util.Map;

public class _12_A_Miner_Task {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> resources = new LinkedHashMap<>();

        enterResources(bf, resources);
        printResources(resources);
    }

    private static void printResources(Map<String, Integer> resources) {
        for (Map.Entry<String, Integer> resource : resources.entrySet()) {
            System.out.printf("%s -> %d%n", resource.getKey(), resource.getValue());
        }
    }

    private static void enterResources(BufferedReader bf, Map<String, Integer> resources) throws IOException {
        while (true) {
            String resource = bf.readLine();

            if ("stop".equalsIgnoreCase(resource)) {
                break;
            }

            int quantity = Integer.valueOf(bf.readLine());

            if (!resources.containsKey(resource)) {
                resources.put(resource, 0);
            }

            quantity += resources.get(resource);
            resources.put(resource, quantity);
        }
    }
}
