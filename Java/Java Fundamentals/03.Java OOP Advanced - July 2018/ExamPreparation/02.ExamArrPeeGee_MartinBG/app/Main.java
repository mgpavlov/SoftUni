package ExamArrPeeGee.app;

import ExamArrPeeGee.app.contracts.*;
import ExamArrPeeGee.app.core.BattlefieldImplementation;
import ExamArrPeeGee.app.engine.EngineImpl;
import ExamArrPeeGee.app.factory.ActionFactoryImpl;
import ExamArrPeeGee.app.factory.SpecialityFactoryImpl;
import ExamArrPeeGee.app.factory.TargetableFactoryImpl;
import ExamArrPeeGee.app.io.ConsoleReader;
import ExamArrPeeGee.app.io.ConsoleWriter;

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
