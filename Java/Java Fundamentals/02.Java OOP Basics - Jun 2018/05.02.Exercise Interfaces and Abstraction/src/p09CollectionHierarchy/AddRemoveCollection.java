package p09CollectionHierarchy;

import java.util.List;

public class AddRemoveCollection extends BaseCollection implements Remove{
    public AddRemoveCollection(List<String> list) {
        super(list);
    }

    @Override
    public String remove() {
        return super.list.remove(super.list.size() - 1);
    }

    @Override
    public int add(String ele) {
        super.list.add(0, ele);
        return 0;
    }
}
