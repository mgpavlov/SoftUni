package temp.core.commands;

import temp.core.BaseCommand;
import cresla.io.ConsoleWriter;

import java.lang.reflect.InvocationTargetException;

public class ReactorCommand extends BaseCommand {

    protected ReactorCommand(ConsoleWriter consoleWriter) {
        super(consoleWriter);
    }

    @Override
    public void execute() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {

        super.getConsoleWriter().writeLine(super.getManager().reactorCommand(super.getParams()));
    }
}
