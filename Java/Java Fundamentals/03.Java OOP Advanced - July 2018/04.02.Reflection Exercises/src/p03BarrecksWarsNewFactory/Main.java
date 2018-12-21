package p03BarrecksWarsNewFactory;

import p03BarrecksWarsNewFactory.contracts.Repository;
import p03BarrecksWarsNewFactory.contracts.Runnable;
import p03BarrecksWarsNewFactory.contracts.UnitFactory;
import p03BarrecksWarsNewFactory.core.Engine;
import p03BarrecksWarsNewFactory.core.factories.UnitFactoryImpl;
import p03BarrecksWarsNewFactory.data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Runnable engine = new Engine(repository, unitFactory);
		engine.run();
	}
}
