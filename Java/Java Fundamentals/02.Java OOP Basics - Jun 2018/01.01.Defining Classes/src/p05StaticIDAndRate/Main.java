package p05StaticIDAndRate;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<Integer, BankAccount> accounts = new HashMap<>();
    private static int id = 0;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.nextLine();

            if ("End".equals(input)) {
                break;
            }
            String[] area = input.split("\\s+");

            String command = (area[0]);

            switch (command) {
                case "Create":
                    id++;
                    accounts.put(id, new BankAccount());
                    accounts.get(id).setId(id);
                    System.out.println("Account " + accounts.get(id).toString() + " created");

                    break;
                case "Deposit":
                    int targetId = Integer.valueOf(area[1]);
                    if (accounts.containsKey(targetId)) {
                        accounts.get(targetId).deposit(Double.valueOf(area[2]));
                        DecimalFormat df = new DecimalFormat("#.##");
                        System.out.printf("Deposited %s to %s%n", df.format(Double.valueOf(area[2])), accounts.get(targetId).toString());
                    } else {
                        System.out.println("Account does not exist");
                    }
                    break;
                case "SetInterest":
                    BankAccount.setInterestRate(Double.valueOf(area[1]));
                    break;
                case "GetInterest":
                    targetId = Integer.valueOf(area[1]);
                    if (accounts.containsKey(targetId)) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        System.out.println(df.format(accounts.get(targetId).getInterest(Integer.valueOf(area[2]))));
                    } else {
                        System.out.println("Account does not exist");
                    }
                    break;
            }
        }
    }
}
