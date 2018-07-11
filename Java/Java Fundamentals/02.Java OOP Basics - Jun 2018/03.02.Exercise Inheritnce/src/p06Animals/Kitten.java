package p06Animals;

public class Kitten extends Animal {

    protected Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    protected void produceSound() {
        System.out.println("Miau");
    }
}
