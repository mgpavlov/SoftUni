package app;


import app.contracts.*;
import app.core.BattlefieldImplementation;
import app.engine.EngineImpl;
import app.factory.ActionFactoryImpl;
import app.factory.SpecialityFactoryImpl;
import app.factory.TargetableFactoryImpl;
import app.io.ConsoleReader;
import app.io.ConsoleWriter;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {

        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();
        ActionFactory actionFactory = new ActionFactoryImpl();
        TargetableFactory targetableFactory = new TargetableFactoryImpl();
        SpecialityFactory specialityFactory = new SpecialityFactoryImpl();

        Battlefield battlefield = new BattlefieldImplementation(writer, targetableFactory, actionFactory, specialityFactory);

        Engine engine = new EngineImpl(battlefield, reader, writer);
        engine.run();
    }
}
