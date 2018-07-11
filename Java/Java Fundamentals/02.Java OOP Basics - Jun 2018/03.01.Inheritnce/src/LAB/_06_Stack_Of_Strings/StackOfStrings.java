package _03_Inheritance.LAB._06_Stack_Of_Strings;

import java.util.ArrayList;

public class StackOfStrings {

    private ArrayList<String> data;


    public StackOfStrings() {
        setData(new ArrayList<>());
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    public void push(String item) {
        this.data.add(item);
    }

    public String pop() {
        return this.data.remove(this.data.size() - 1);
    }

    public String peek() {

        String current = this.data.remove(this.data.size() - 1);
        this.data.add(current);

        return current;
    }

    public boolean isEmpty() {
        if (this.data.size() == 0) {
            return true;
        }
        return false;
    }


}
