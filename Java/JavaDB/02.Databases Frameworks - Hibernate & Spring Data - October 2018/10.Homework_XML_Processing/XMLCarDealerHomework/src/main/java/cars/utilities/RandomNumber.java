package cars.utilities;

public final class RandomNumber {

    private RandomNumber() {}

    public static int getRandomNumber(int bound) {
        return (int) (Math.random() * (bound + 1));
    }

    public static int getRandomNumber(int min, int max) {
        return (int) (min + (Math.random() * (max - min)));
    }
}
