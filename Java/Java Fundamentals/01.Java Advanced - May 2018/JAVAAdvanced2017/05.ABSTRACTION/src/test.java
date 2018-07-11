import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int peshoDamage = Integer.parseInt(scanner.nextLine());
        int goshoDamage = Integer.parseInt(scanner.nextLine());
        String pesho = "Pesho";
        String gosho = "Gosho";
        String peshoAttack = "Roundhouse kick";
        String goshoAttack = "Thunderous fist";
        int peshoHealth = 100;
        int goshoHealth = 100;
        int restoration = 10;
        int loopCounter = 1;

        for (int i = 1; i <= loopCounter; i++) {
            if (i % 2 == 1) {
                goshoHealth -= peshoDamage;
                loopCounter--;
                if (goshoHealth <= 0) {
                    System.out.printf("%s won in %dth round.", pesho, loopCounter);
                } else {
                    System.out.printf("%s used %s and reduced %s to %d health.%n", pesho, peshoAttack, gosho, goshoHealth);
                }
            } else {
                peshoHealth -= goshoDamage;
                loopCounter--;
                if (peshoHealth <= 0) {
                    System.out.printf("%s won in %dth round.", gosho, loopCounter);
                } else {
                    System.out.printf("%s used %s and reduced %s to %d health.\n", gosho, goshoAttack, pesho, peshoHealth);
                }
            }
            if (i % 3 == 0) {

                goshoHealth += restoration;
                peshoHealth += restoration;
            }
            loopCounter++;
        }
    }
}

