package app.io;

import app.contracts.Reader;
import app.factory.BaseStructureFactory;

import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleReader implements Reader {

    private BufferedReader bf;

    public ConsoleReader() {
        this.bf = BaseStructureFactory.createBufferedReader();
    }

    @Override
    public String readLine() throws IOException {
        return bf.readLine();
    }
}
