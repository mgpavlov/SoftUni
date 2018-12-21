package p02GenericArrayCreator;

public class Main {
    public static void main(String[] args) {

       String[] arr = ArrayCreator.create(String.class, 10, "strings");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
