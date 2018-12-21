package p04BarracksWarsTheCommandsStrikeBack.contracts;

import jdk.jshell.spi.ExecutionControl;
import p04BarracksWarsTheCommandsStrikeBack.contracts.Unit;

public interface UnitFactory {

    Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException;
}