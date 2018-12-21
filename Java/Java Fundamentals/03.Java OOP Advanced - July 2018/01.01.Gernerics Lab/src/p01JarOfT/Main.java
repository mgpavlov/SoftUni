package p01JarOfT;

public class Main {
    public static void main(String[] args) {

        Pickle pickle = new Pickle();
        Jar<Pickle> jar = new Jar<>();
        jar.add(pickle);
        jar.remove();
    }
}
