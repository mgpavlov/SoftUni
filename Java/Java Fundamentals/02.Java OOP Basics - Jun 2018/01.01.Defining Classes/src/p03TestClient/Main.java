package p03TestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, BankAccount> accounts = new HashMap<>();

        while (true) {
            String[] input = reader.readLine().split(" ");
            if ("End".equals(input[0])) {
                break;
            }
            int id = Integer.parseInt(input[1]);

            switch (input[0]){
                case "Create":
                    if (accounts.containsKey(id)){
                        System.out.println("Account already exists");
                    }else {
                        BankAccount account = new BankAccount();
                        account.setId(id);
                        accounts.putIfAbsent(id, account);
                    }

                    break;
                case  "Deposit":
                    if (!accounts.containsKey(id)){
                        System.out.println("Account does not exist");
                    }else{
                        accounts.get(id).deposit(Double.parseDouble(input[2]));
                    }
                    break;
                case  "Withdraw":
                    if (!accounts.containsKey(id)){
                        System.out.println("Account does not exist");
                    }else {
                        if (Double.parseDouble(input[2]) > accounts.get(id).getBalance() ){
                            System.out.println("Insufficient balance");
                        }else{
                            accounts.get(id).withdraw(Double.parseDouble(input[2]));
                        }
                    }
                    break;
                case "Print":
                    if (!accounts.containsKey(id)){
                        System.out.println("Account does not exist");
                    }else {
                        System.out.println(String.format("Account ID%d, balance %.2f", id, accounts.get(id).getBalance()));
                    }
                    break;
            }
        }
    }
}
