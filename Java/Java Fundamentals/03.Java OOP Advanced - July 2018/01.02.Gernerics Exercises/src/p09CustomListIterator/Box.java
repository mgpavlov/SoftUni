package p09CustomListIterator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Box <T extends Comparable<T>>{
    private List<T> container;

    public Box() {
        this.container = new ArrayList<>();
    }

    public void add (T element){
        this.container.add(element);
    }
    public void remove (int index){
        this.container.remove(index);
    }
    public boolean contains (T element){
        return this.container.contains(element);
    }
    public void swap (int index1, int index2){
        T temp = this.container.get(index1);
        this.container.set(index1, this.container.get(index2));
        this.container.set(index2, temp);
    }

    public int countGreaterThan (T comparator){
        int elementsCount = 0;
        for (T t : this.container) {
            if (t.compareTo(comparator) > 0){
                elementsCount++;
            }
        }
        return elementsCount;
    }
    public T getMax (){
        return this.container.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0);
    }
    public T getMin (){
        return this.container.stream().sorted().collect(Collectors.toList()).get(0);
    }

    public  void sort(){
        Collections.sort(this.container);
    }

    public List<T> getContainer() {
        return this.container;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T data : this.container) {
            sb.append(data).append(System.lineSeparator());
        }
       return sb.toString().trim();
    }
}
