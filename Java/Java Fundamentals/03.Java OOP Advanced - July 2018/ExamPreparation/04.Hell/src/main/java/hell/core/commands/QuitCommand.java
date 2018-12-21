package hell.core.commands;

import hell.core.BaseCommand;

public class QuitCommand extends BaseCommand {
    @Override
    public void execute() throws IllegalAccessException {
        super.getCommandController().quit();
    }
}
