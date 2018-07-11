package p09CollectionHierarchy;

import java.util.List;

public abstract class BaseCollection {
    protected List<String> list;

    public BaseCollection(List<String> list) {
        this.list = list;
    }
}
