package hell.core;


import hell.annotations.Inject;
import hell.interfaces.CommandController;
import hell.interfaces.Executable;
import hell.interfaces.Inventory;

import java.util.Collections;
import java.util.List;

public abstract class BaseCommand implements Executable {

    @Inject
    private List<String> params;
    @Inject
    private CommandController commandController;

    protected BaseCommand() {
    }

    protected List<String> getParams() {
        return Collections.unmodifiableList(this.params);
    }

    protected CommandController getCommandController() {
        return this.commandController;
    }
}