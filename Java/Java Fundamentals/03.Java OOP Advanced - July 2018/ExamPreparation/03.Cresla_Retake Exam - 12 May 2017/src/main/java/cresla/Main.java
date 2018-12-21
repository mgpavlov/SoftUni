package cresla;

import cresla.interfaces.*;
import cresla.io.ConsoleReader;
import cresla.io.ConsoleWriter;
import cresla.manager.CommandsManager;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchFieldException {
        OutputWriter writer = new ConsoleWriter();
        InputReader reader = new ConsoleReader();
        Manager commandsManager = new CommandsManager();
       /* ModuleFactory moduleFactory = new ModuleFactoryImpl();
        ReactorFactory reactorFactory = new ReactorFactoryImpl();

        Manager manager = new ManagerImpl(writer, moduleFactory, reactorFactory);
        CommandHandler commandHandler = new CommandHandlerImpl(manager);

        Engine engine = new ReactorEngine(reader, commandHandler);
        engine.run();*/

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
