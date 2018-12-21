package temp.core.commands;

import temp.core.BaseCommand;
import cresla.io.ConsoleWriter;

import java.lang.reflect.InvocationTargetException;

public class ExitCommand extends BaseCommand {
    protected ExitCommand(ConsoleWriter consoleWriter) {
        super(consoleWriter);
    }

    @Override
    public void execute() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        super.getConsoleWriter().writeLine(super.getManager().exitCommand(null));
    }
}
