package hell.engines;

import hell.interfaces.CommandHandler;
import hell.interfaces.Engine;
import hell.interfaces.InputReader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameEngine implements Engine {
    private static final String TERMINATE_COMMAND = "Quit";

    private InputReader reader;
    private CommandHandler commandHandler;

    public GameEngine(InputReader reader, CommandHandler handler) {
        this.reader = reader;
        this.commandHandler = handler;
    }

    @Override
    public void run() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String line;
        while (true){
            if (TERMINATE_COMMAND.equals(line = this.reader.readLine())) {
                this.commandHandler.executeCommand(line, null);
                break;
            }
            String[] lineTokens = line.split("\\s+");

            this.commandHandler.executeCommand(lineTokens[0], Arrays.stream(lineTokens).skip(1).collect(Collectors.toList()));
        }
    }
}
