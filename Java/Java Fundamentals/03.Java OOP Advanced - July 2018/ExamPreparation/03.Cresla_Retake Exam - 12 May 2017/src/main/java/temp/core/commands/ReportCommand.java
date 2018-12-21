package temp.core.commands;

import temp.core.BaseCommand;
import cresla.io.ConsoleWriter;

import java.lang.reflect.InvocationTargetException;

public class ReportCommand extends BaseCommand {
    protected ReportCommand(ConsoleWriter consoleWriter) {
        super(consoleWriter);
    }

    @Override
    public void execute() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        super.getConsoleWriter().writeLine(super.getManager().reportCommand(super.getParams()));
    }
}
