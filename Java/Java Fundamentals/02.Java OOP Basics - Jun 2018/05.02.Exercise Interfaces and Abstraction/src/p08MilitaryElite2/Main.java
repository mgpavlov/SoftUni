package p08MilitaryElite2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//75 pts
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Private> privates = new HashMap<>();
        while (true) {
            String[] input = reader.readLine().split("\\s+");
            if ("End".equals(input[0])) {
                break;
            }
            String id = input[1];
            String firstName = input[2];
            String lastName = input[3];
            switch (input[0]) {
                case "Private":
                    double salary = Double.parseDouble(input[4]);
                    Private aPrivate = new Private(id, firstName, lastName, salary);
                    privates.putIfAbsent(id, aPrivate);
                    System.out.println(aPrivate.toString());
                    break;
                case "LeutenantGeneral":
                    salary = Double.parseDouble(input[4]);
                    LeutenantGeneral leutenantGeneral = new LeutenantGeneral(id, firstName, lastName, salary);
                    for (int i = 5; i < input.length; i++) {
                        String privateId = input[i];
                        if (privates.containsKey(privateId)) {
                            leutenantGeneral.addPrivate(privates.get(privateId));
                        }
                    }
                    System.out.print(leutenantGeneral.toString());
                    break;
                case "Engineer":
                    salary = Double.parseDouble(input[4]);
                    String corps = input[5];
                    Engineer engineer = null;
                    try {
                        engineer = new Engineer(id, firstName, lastName, salary, corps);
                    } catch (IllegalArgumentException ex) {
                        continue;
                    }
                    for (int i = 6; i < input.length; i += 2) {
                        Repair repair = new Repair(input[i], input[i + 1]);
                        engineer.addRepair(repair);
                    }
                    System.out.print(engineer.toString());
                    break;
                case "Commando":
                    salary = Double.parseDouble(input[4]);
                    corps = input[5];
                    Commando commando = null;
                    try {
                        commando = new Commando(id, firstName, lastName, salary, corps);
                        for (int i = 6; i < input.length; i += 2) {
                            try {
                                Mission mission = new Mission(input[i], input[i + 1]);
                                commando.addMission(mission);
                            }catch (IllegalArgumentException ignored){
                            }

                        }
                        System.out.print(commando.toString());
                    } catch (IllegalArgumentException ex) {
                        continue;
                    }


                    break;
                case "Spy":
                    String codeNumber = input[4];
                    Spy spy = new Spy(id, firstName, lastName, codeNumber);
                    System.out.print(spy.toString());
            }
        }
    }
}
