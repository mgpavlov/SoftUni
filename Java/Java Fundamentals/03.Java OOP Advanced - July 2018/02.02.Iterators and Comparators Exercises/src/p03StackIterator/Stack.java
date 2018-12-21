package p03StackIterator;

import java.util.*;

public class Stack <T> implements  Iterable<T>{
    private List<T> stack;

    public Stack() {
        this.stack = new ArrayList<T>();
    }

    public void push(T element){
        this.stack.add(element);
    }
    public T pop(){
        if (this.stack.size()== 0){
            throw new IllegalArgumentException("No elements");
        }
        return this.stack.remove(this.stack.size()-1);
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<T> {
        private int index;
        private StackIterator() {
            this.index = stack.size() - 1;
        }
        @Override
        public boolean hasNext() {
            return index>=0;
        }

        @Override
        public T next() {
            return stack.get(this.index--);
        }
    }
}
