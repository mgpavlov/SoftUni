package p03BarrecksWarsNewFactory.core.factories;

import jdk.jshell.spi.ExecutionControl;
import p03BarrecksWarsNewFactory.contracts.Unit;
import p03BarrecksWarsNewFactory.contracts.UnitFactory;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"pr0304Barracks.models.units.";

	@Override
	public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
		// TODO: implement for problem 3
		throw new ExecutionControl.NotImplementedException("message");
	}
}
