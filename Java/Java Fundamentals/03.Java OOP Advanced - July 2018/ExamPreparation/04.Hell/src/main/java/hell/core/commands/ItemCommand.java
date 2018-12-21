package hell.core.commands;

import hell.core.BaseCommand;

public class ItemCommand extends BaseCommand {
    @Override
    public void execute() throws IllegalAccessException {
        super.getCommandController().createCommonItem(super.getParams().get(0),
                super.getParams().get(1),
                Integer.valueOf(super.getParams().get(2)),
                Integer.valueOf(super.getParams().get(3)),
                Integer.valueOf(super.getParams().get(4)),
                Integer.valueOf(super.getParams().get(5)),
                Integer.valueOf(super.getParams().get(6))
                );
    }
}
