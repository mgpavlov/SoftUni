package p04DefinePersonClass;

import p03TestClient.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class Person {
    String name;
    int age;
    List<BankAccount> accounts;

    public Person(String name, int age){
        this(name, age, new ArrayList<>());
    }

    public Person(String name, int age, List<BankAccount> accounts) {
        this.name = name;
        this.age = age;
        this.accounts = accounts;

    }

    public double getBalance(){
        return this.accounts.stream().mapToDouble(e->e.getBalance()).sum();
    }
}
