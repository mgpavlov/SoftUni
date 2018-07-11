package ExamPrep2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CarManager carManager = new CarManager();

        while (true) {
            //String[] input = reader.readLine().split("\\s+");
            String command = reader.readLine();
            if ("Cops Are Here".equals(command)) {
                break;
            }
            String[] input = command.split("\\s+");
            switch (input[0]) {
                case "register":
                    carManager.register(Integer.parseInt(input[1]), input[2], input[3], input[4],
                            Integer.parseInt(input[5]), Integer.parseInt(input[6]), Integer.parseInt(input[7]),
                            Integer.parseInt(input[8]), Integer.parseInt(input[9]));
                    break;
                case "open":
                    if (input.length == 6){
                        carManager.open(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), input[4], Integer.parseInt(input[5]), -1);
                    }else if (input.length == 7){
                        carManager.open(Integer.parseInt(input[1]), input[2], Integer.parseInt(input[3]), input[4], Integer.parseInt(input[5]), Integer.parseInt(input[6]));
                    }
                    break;
                case "participate":
                    carManager.participate(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
                    break;
                case "check":
                    System.out.println(carManager.check(Integer.parseInt(input[1])));
                    break;
                case "start":
                    System.out.println(carManager.start(Integer.parseInt(input[1])));
                    break;
                case "park":
                    carManager.park(Integer.parseInt(input[1]));
                    break;
                case "unpark":
                    carManager.unpark(Integer.parseInt(input[1]));
                    break;
                case "tune":
                    carManager.tune(Integer.parseInt(input[1]), input[2]);
                    break;
            }
        }
    }
}
