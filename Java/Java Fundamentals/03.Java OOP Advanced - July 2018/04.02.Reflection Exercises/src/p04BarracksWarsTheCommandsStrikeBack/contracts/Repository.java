package p04BarracksWarsTheCommandsStrikeBack.contracts;

import jdk.jshell.spi.ExecutionControl;
import p04BarracksWarsTheCommandsStrikeBack.contracts.Unit;

public interface Repository {

	void addUnit(Unit unit);

	String getStatistics();

	void removeUnit(String unitType) throws ExecutionControl.NotImplementedException;
}
