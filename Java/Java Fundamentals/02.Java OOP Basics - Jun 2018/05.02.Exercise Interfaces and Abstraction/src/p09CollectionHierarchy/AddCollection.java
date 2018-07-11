package p09CollectionHierarchy;

import java.util.List;

public class AddCollection extends BaseCollection implements Add{
    public AddCollection(List<String> list) {
        super(list);
    }

    @Override
    public int add(String ele) {
        super.list.add(ele);
        return super.list.size() - 1;
    }
}
