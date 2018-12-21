package temp.core.commands;

import temp.core.BaseCommand;
import cresla.io.ConsoleWriter;

public class ModuleCommand extends BaseCommand {
    protected ModuleCommand(ConsoleWriter consoleWriter) {
        super(consoleWriter);
    }

    @Override
    public void execute() throws IllegalAccessException {
        super.getConsoleWriter().writeLine(super.getManager().moduleCommand(super.getParams()));
    }
}
