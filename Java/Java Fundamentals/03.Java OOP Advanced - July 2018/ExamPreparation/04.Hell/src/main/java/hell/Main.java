package hell;

import hell.controllers.CommandControllerImpl;
import hell.engines.GameEngine;
import hell.factories.HeroFactoryImpl;
import hell.factories.ItemFactoryImpl;
import hell.factories.RecipeFactoryImpl;
import hell.handlers.CommandHandlerImpl;
import hell.interfaces.*;
import hell.io.ConsoleReader;
import hell.io.ConsoleWriter;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        OutputWriter writer = new ConsoleWriter();
        InputReader reader = new ConsoleReader();

        HeroFactory heroFactory = new HeroFactoryImpl();
        RecipeFactory recipeFactory = new RecipeFactoryImpl();
        ItemFactory itemFactory = new ItemFactoryImpl();

        CommandController controller = new CommandControllerImpl(heroFactory, recipeFactory, itemFactory, writer);
        CommandHandler handler = new CommandHandlerImpl(controller);

        Engine engine = new GameEngine(reader, handler);
        engine.run();
    }
}