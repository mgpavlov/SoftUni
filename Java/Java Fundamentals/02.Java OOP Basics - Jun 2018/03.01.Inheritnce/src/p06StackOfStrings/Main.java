package p06StackOfStrings;

public class Main {
    public static void main(String[] args) {
     StackOfStrings s = new StackOfStrings();
     s.push("one");
     s.push("two");
     s.push("three");

        System.out.println(s.isEmpty());
        System.out.println(s.peek());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}
