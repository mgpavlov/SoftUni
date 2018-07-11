package ExamPrep4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] colonyParameters = reader.readLine().split("\\s+");
        int colonyMaxFamilyCount = Integer.parseInt(colonyParameters[0]);
        int FamilyMaxCapacity = Integer.parseInt(colonyParameters[1]);
        Commands commands = new Commands(colonyMaxFamilyCount, FamilyMaxCapacity);
        while (true) {
            String input = reader.readLine();
            if ("end".equals(input)) {
                break;
            }
            String[] tokens = input.split("\\s+");
            switch (tokens[0]){
                case "insert":
                    if (tokens.length == 7)commands.insertMedics(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]), tokens[6]);
                    commands.insert(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                    break;
                case "remove":
                    break;
                case "grow":
                    break;
                case "capacity":
                    break;
                case "family":
                    break;
            }
        }
    }
}
