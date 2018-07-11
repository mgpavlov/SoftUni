package p04MordorsCrueltyPlan;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> eatenFoods = Arrays.asList(scan.nextLine().split("\\s+"));

        Wizard gandalf = new Wizard();

        gandalf.setHappinessIndex(eatenFoods);

        System.out.println(gandalf.getHappinessIndex());
        System.out.println(gandalf.getMood());
    }
}
