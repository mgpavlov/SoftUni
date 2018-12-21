package temp.engine;

import cresla.interfaces.InputReader;
import temp.interfaces.CommandHandler;
import temp.interfaces.Engine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ReactorEngine implements Engine {
    private static final String TERMINATE_COMMAND = "Exit";

    private InputReader reader;
    private CommandHandler commandHandler;

    public ReactorEngine(InputReader reader, CommandHandler commandHandler) {
        this.reader = reader;
        this.commandHandler = commandHandler;
    }

    @Override
    public void run() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
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
