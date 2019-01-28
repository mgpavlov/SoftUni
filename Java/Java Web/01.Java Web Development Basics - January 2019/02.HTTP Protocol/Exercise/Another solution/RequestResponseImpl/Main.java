package RequestResponseImpl;

import RequestResponseImpl.io.ConsoleReaderImpl;
import RequestResponseImpl.io.ConsoleWriterImpl;
import RequestResponseImpl.io.Reader;
import RequestResponseImpl.io.Writer;

public class Main {
    public static void main(String[] args) {

        Reader reader = new ConsoleReaderImpl();
        Writer writer = new ConsoleWriterImpl();

        App httpApp = new App(reader, writer);
        httpApp.run();
    }
}
