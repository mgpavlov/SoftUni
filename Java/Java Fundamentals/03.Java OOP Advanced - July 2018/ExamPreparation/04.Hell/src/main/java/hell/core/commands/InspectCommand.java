package hell.core.commands;

import hell.core.BaseCommand;

public class InspectCommand extends BaseCommand {
    @Override
    public void execute() throws IllegalAccessException {
        super.getCommandController().inspectHero(super.getParams().get(0));
    }
}
