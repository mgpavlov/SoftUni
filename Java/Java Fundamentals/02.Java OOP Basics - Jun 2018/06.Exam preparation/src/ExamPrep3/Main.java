package ExamPrep3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Commands commandManager = new Commands();
        while (true) {
            String input = reader.readLine();
            if ("Quit".equals(input)) {
                commandManager.quit();
                break;
            }
            String[]commands = input.split("\\s+");
            switch (commands[0]){
                case "Bender":
                    commandManager.bender(commands[1], commands[2], Integer.parseInt(commands[3]), Double.parseDouble(commands[4]));
                    break;
                case "Monument":
                    commandManager.monument(commands[1], commands[2], Integer.parseInt(commands[3]));
                    break;
                case "Status":
                    commandManager.status(commands[1]);
                    break;
                case "War":
                    commandManager.war(commands[1]);
                    break;
            }
        }
    }
}
