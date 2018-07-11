package p09CollectionHierarchy;

import java.util.BitSet;
import java.util.List;

public class MyList extends BaseCollection implements Used{
    public MyList(List<String> list) {
        super(list);
    }

    @Override
    public String remove() {
        return super.list.remove(0);
    }

    @Override
    public int add(String ele) {
        super.list.add(0, ele);
        return 0;
    }

    @Override
    public int used() {
        return super.list.size();
    }
}
