package p01JarOfT;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<T> {

    private Deque<T> items;

    public Jar() {
        this.items = new ArrayDeque<T>();
    }

    public void add(T element){
        this.items.add(element);
    }
    public T remove (){
        if (this.items.size()>0){
            return this.items.getLast();
        }
        return null;
    }
}
