package panzer;

import panzer.contracts.Manager;
import panzer.managers.PanzerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Manager manager = new PanzerManager();

        while (true){
            String line = reader.readLine();
            if ("Terminate".equals(line)){
                System.out.println(manager.terminate(null));
                break;
            }
            String[] tokens = line.split("\\s+");
            List<String> arguments = Arrays.stream(tokens).skip(1).collect(Collectors.toList());
            switch (tokens[0]){
                case "Vehicle":
                    System.out.println(manager.addVehicle(arguments));
                    break;
                case "Part":
                    System.out.println(manager.addPart(arguments));
                    break;
                case "Inspect":
                    System.out.println(manager.inspect(arguments));
                    break;
                case "Battle":
                    System.out.println(manager.battle(arguments));
                    break;
            }
        }
    }
}
