package p10InfernoInfinity;

import p10InfernoInfinity.enums.GemType;
import p10InfernoInfinity.enums.WeaponType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CommandManager commandManager = new CommandManager();

        String command = reader.readLine();
        while (!"END".equals(command)) {
            String[] commandArgs = command.split(";");
            String commandType = commandArgs[0];
            if (commandType.equals("Create")) {
                commandManager.createWeapon(WeaponType.valueOf(commandArgs[1]), commandArgs[2]);
            } else if (commandType.equals("Add")) {
                commandManager.addGem(commandArgs[1], Integer.parseInt(commandArgs[2]), GemType.valueOf(commandArgs[3]));
            } else if (commandType.equals("Remove")) {
                commandManager.removeGem(commandArgs[1], Integer.parseInt(commandArgs[2]));
            } else if (commandType.equals("Print")) {
                String weapon = commandManager.print(commandArgs[1]);
                System.out.println(weapon.trim());
            } else if (commandType.equals("Compare")) {
                String strongestWeapon = commandManager.compare(commandArgs[1], commandArgs[2]);
                System.out.println(strongestWeapon.trim());
            } else if (commandType.equals("Author")) {
                String author = commandManager.extractAuthor();
                System.out.println("Author: " + author);
            } else if (commandType.equals("Revision")) {
                int revisions = commandManager.extractRevisions();
                System.out.println("Revision: " + revisions);
            } else if (commandType.equals("Description")) {
                String description = commandManager.extractDescription();
                System.out.println("Class description: " + description);
            } else if (commandType.equals("Reviewers")) {
                String reviewers = commandManager.extractReviewers();
                System.out.println("Reviewers: " + reviewers);
            }

            command = reader.readLine();
        }
    }
}
