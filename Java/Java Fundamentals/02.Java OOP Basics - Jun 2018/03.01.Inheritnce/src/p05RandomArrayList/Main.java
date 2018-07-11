package p05RandomArrayList;

public class Main {
    public static void main(String[] args) {
        RandomArrayList arr = new RandomArrayList(){{
            add(1);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
        }};

        System.out.println(arr.getRandomElement());
    }
}
