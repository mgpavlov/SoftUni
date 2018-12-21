package cresla;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.Reader;
import cresla.io.Writer;
import cresla.managers.CommandsManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        InputReader reader = new Reader();
        OutputWriter writer = new Writer();
        Manager commandsManager = new CommandsManager();

        terminate:
        while (true) {
            List<String> tokens = Arrays.asList(reader.readLine().split("\\s+"));

            switch (tokens.get(0)) {
                case "Reactor":
                    writer.writeLine(commandsManager.reactorCommand(tokens));
                    break;
                case "Module":
                    writer.writeLine(commandsManager.moduleCommand(tokens));
                    break;
                case "Report":
                    writer.writeLine(commandsManager.reportCommand(tokens));
                    break;
                case "Exit":
                    writer.writeLine(commandsManager.exitCommand(tokens));
                    break terminate;
            }
        }
    }
}
