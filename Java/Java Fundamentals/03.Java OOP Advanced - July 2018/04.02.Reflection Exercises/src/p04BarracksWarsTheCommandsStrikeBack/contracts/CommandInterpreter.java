package p04BarracksWarsTheCommandsStrikeBack.contracts;

import p04BarracksWarsTheCommandsStrikeBack.contracts.Executable;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
