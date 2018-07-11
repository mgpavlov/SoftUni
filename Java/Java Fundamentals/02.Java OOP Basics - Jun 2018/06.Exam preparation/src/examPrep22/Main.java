package examPrep22;

import examPrep22.core.CarManager;
import examPrep22.core.Engine;
import examPrep22.io.ConsoleInputReader;
import examPrep22.io.ConsoleOutputWriter;
import examPrep22.utilities.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        ConsoleInputReader inputReader = new ConsoleInputReader();
        ConsoleOutputWriter outputWriter = new ConsoleOutputWriter();
        InputParser inputParser = new InputParser();
        CarManager carManager = new CarManager();
        Engine engine = new Engine(inputReader, outputWriter, inputParser, carManager);

        engine.run();
    }
}
