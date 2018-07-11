package ExamPrep1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HealthManager healthManager = new HealthManager();

        while (true) {
            String command = reader.readLine();
            if ("BEER IS COMING".equals(command)) {
                break;
            }
            String[] input = command.split("\\s++");
            switch (input[0]) {
                case "createOrganism":
                    System.out.print(healthManager.createOrganism(input[1]));
                    break;
                case "addCluster":
                    System.out.print(healthManager.addCluster(input[1], input[2], Integer.parseInt(input[3]), Integer.parseInt(input[4])));
                    break;
                case "addCell":
                    System.out.print(healthManager.addCell(input[1], input[2], input[3], input[4], Integer.parseInt(input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]), Integer.parseInt(input[8])));
                    break;
                case "checkCondition":
                    System.out.print(healthManager.checkCondition(input[1]));
                    break;
                case "activateCluster":
                    /*System.out.print(healthManager.activateCluster(input[1]));*/
                    break;
            }
        }
    }
}
