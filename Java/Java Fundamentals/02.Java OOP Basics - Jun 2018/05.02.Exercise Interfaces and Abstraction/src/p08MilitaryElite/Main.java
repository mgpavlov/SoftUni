package p08MilitaryElite;

import p08MilitaryElite.interfaces.*;
import p08MilitaryElite.models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
//75 pts
public class Main {
    private static Map<Integer, Private> privateById = new HashMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        String command = "";
        while (!"End".equals(command = in.nextLine())) {
            String[] commandArgs = command.split("\\s+");

            String soldierType = commandArgs[0];
            switch (soldierType) {
                case "Private":
                    Private privateSoldier = (Private) new PrivateImpl(Integer.parseInt(commandArgs[1]), commandArgs[2], commandArgs[3], Double.parseDouble(commandArgs[4]));
                    privateById.put(Integer.parseInt(commandArgs[1]), privateSoldier);
                    System.out.println(privateSoldier);
                    break;
                case "Spy":
                    Spy spy = new SpyImpl(Integer.parseInt(commandArgs[1]), commandArgs[2], commandArgs[3], Integer.parseInt(commandArgs[4]));
                    System.out.println(spy);
                    break;
                case "LeutenantGeneral":
                    LeutenantGeneral leutenantGeneral = (LeutenantGeneral) new LeutenantGeneralImp(
                            Integer.parseInt(commandArgs[1]), commandArgs[2], commandArgs[3], Double.parseDouble(commandArgs[4]));
                    addPrivatesTo(leutenantGeneral, commandArgs);
                    System.out.println(leutenantGeneral);
                    break;
                case "Engineer":
                    Engineer engineer = (Engineer) new EngineerImpl(
                            Integer.parseInt(commandArgs[1]), commandArgs[2], commandArgs[3], Double.parseDouble(commandArgs[4]), commandArgs[5]);

                    if (engineer.getCorps() == null) {
                        continue;
                    } else {
                        getRepairs(engineer, commandArgs);
                        System.out.println(engineer);
                    }
                    break;
                case "Commando":
                    Commando commando = (Commando) new CommandoImpl(
                            Integer.parseInt(commandArgs[1]), commandArgs[2], commandArgs[3], Double.parseDouble(commandArgs[4]), commandArgs[5]);

                    if (commando.getCorps() == null) {
                        continue;
                    } else {
                        getMissions(commando, commandArgs);
                        System.out.println(commando);
                    }
                    break;
            }
        }
    }

    private static void getMissions(Commando commando, String[] commandArgs) {
        for (int i = 6; i < commandArgs.length; i += 2) {
            String codeName = commandArgs[i];
            String state = commandArgs[i + 1];
            Mission mission = (Mission) new MissionImp(codeName, state);
            if (mission.getState() != null) {
                commando.addMission(mission);
            }
        }
    }

    private static void getRepairs(Engineer engineer, String[] commandArgs) {
        for (int i = 6; i < commandArgs.length; i += 2) {
            String partName = commandArgs[i];
            int houresWorked = Integer.parseInt(commandArgs[i + 1]);
            Repair repair = (Repair) new RepairImpl(partName, houresWorked);
            engineer.addRepair(repair);
        }
    }

    private static void addPrivatesTo(LeutenantGeneral leutenantGeneral, String[] commandArgs) {
        for (int i = 5; i < commandArgs.length; i++) {
            int id = Integer.parseInt(commandArgs[i]);
            if (privateById.containsKey(id)) {
                Private privateSoldier = privateById.get(id);
                leutenantGeneral.addPrivate(privateSoldier);
            }
        }
    }
}