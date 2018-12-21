package cresla.io;

import cresla.interfaces.OutputWriter;

public class Writer implements OutputWriter {

    public Writer(){}

    @Override
    public void write(String output) {
        if (output != null) {
            System.out.print(output);
        }
    }

    @Override
    public void writeLine(String output) {
        if (output != null) {
            System.out.println(output);
        }
    }
}
