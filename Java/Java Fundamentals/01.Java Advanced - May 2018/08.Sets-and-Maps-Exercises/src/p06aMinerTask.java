import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class p06aMinerTask {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> result = new LinkedHashMap<>();

        while (true){
            String line = sc.nextLine();
            if (line.equals("stop")){
                break;
            }
            String resource = line;
            int quantity = Integer.parseInt(sc.nextLine());

            if (!result.containsKey(resource)){
                result.put(resource, 0);
            }
            result.put(resource, result.get(resource)+ quantity);
        }
        for (Map.Entry<String, Integer> elementQuantity : result.entrySet()) {
            System.out.printf("%s -> %d%n", elementQuantity.getKey(), elementQuantity.getValue());
        }

    }
}
