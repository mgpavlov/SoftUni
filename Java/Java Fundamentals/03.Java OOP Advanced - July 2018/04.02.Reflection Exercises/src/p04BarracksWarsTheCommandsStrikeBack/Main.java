package p04BarracksWarsTheCommandsStrikeBack;

import p04BarracksWarsTheCommandsStrikeBack.contracts.Repository;
import p04BarracksWarsTheCommandsStrikeBack.contracts.UnitFactory;
import p04BarracksWarsTheCommandsStrikeBack.core.Engine;
import p04BarracksWarsTheCommandsStrikeBack.core.factories.UnitFactoryImpl;
import p04BarracksWarsTheCommandsStrikeBack.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Engine engine = new Engine(repository, unitFactory);
		engine.run();
	}
}
