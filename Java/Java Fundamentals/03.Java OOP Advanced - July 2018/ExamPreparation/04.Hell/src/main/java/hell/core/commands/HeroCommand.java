package hell.core.commands;

import hell.core.BaseCommand;

import java.lang.reflect.InvocationTargetException;

public class HeroCommand extends BaseCommand {
    @Override
    public void execute() throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        super.getCommandController().createHero(super.getParams().get(0), super.getParams().get(1));
    }
}
