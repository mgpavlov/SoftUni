package p02WarningLevels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lowerBoundary = reader.readLine();
        Logger logger = new Logger(lowerBoundary);

        String command = reader.readLine();
        while (!"END".equals(command)) {
            String[] commandArgs = command.split(": ");
            String importance = commandArgs[0];
            String message = commandArgs[1];

            logger.addMessage(importance, message);

            command = reader.readLine();
        }

        for (Message message : logger.getMessages()) {
            System.out.println(message);
        }
    }
}
