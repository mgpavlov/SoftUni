package p04ShoppingSpree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<String> products;

    public Person(String name, double money) {
        this.setName(name);
        this.setMoney(money);
        this.products =  new ArrayList<>();
    }

    private void setName(String name) {
        if (name.trim().length()==0 || name == null){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public double getMoney() {
        return this.money;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void setProducts(String product) {
        this.products.add(product);
    }
    public void reduceMoney(double cost){
        this.setMoney(this.money-cost);
    }

    @Override
    public String toString() {
        if (this.products.isEmpty()){
            return this.name + " - Nothing bought";
        }
        return this.name + " - " + String.join(", ", products);
    }
}
