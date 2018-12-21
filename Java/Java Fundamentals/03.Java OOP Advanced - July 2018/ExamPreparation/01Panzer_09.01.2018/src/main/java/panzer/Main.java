package panzer;

import panzer.contracts.InputReader;
import panzer.contracts.OutputWriter;
import panzer.io.Reader;
import panzer.io.Writer;
import panzer.manager.ProgramManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new Reader();
        OutputWriter writer = new Writer();
        ProgramManager programManager = new ProgramManager();

        while (true) {
            String line = reader.readLine();
            String[] splitted = line.split("\\s+");
            if (splitted[0].equals("Terminate")) {
                System.out.println(programManager.terminate(null));
                break;
            }
            switch(splitted[0]){
                case "Vehicle":
                    writer.println(programManager.addVehicle(Arrays.asList(splitted)));
                    break;
                case "Part":
                    writer.println(programManager.addPart(Arrays.asList(splitted)));
                    break;
                case "Inspect":
                    writer.print(programManager.inspect(Arrays.asList(splitted)));
                    break;
                case "Battle":
                    writer.println(programManager.battle(Arrays.asList(splitted)));
                    break;
            }
        }
    }
}
