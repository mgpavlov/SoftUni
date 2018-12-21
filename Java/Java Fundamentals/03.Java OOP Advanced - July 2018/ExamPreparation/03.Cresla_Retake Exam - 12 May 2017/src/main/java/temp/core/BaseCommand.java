package temp.core;

import temp.annotations.Inject;
import cresla.interfaces.Manager;
import cresla.io.ConsoleWriter;
import temp.interfaces.Executable;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements Executable {
    @Inject
    private List<String> params;
    @Inject
    private Manager manager;
    private ConsoleWriter consoleWriter;

    protected BaseCommand(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
    }

    protected List<String> getParams() {
        return Collections.unmodifiableList(this.params);
    }

    protected Manager getManager() {
        return this.manager;
    }
    protected ConsoleWriter getConsoleWriter() {
        return this.consoleWriter;
    }

}
