package p09LinkedListTraversal.iterfaces;

public interface LinkedList<E> extends Iterable<E> {
    int getSize();

    void add(E element);

    boolean remove(E element);
}
