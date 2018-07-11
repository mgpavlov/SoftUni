package p01p02DefineBankAccountClass;

public class BankAccount {
    public int id;
    public double balance;

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount){
            this.balance += amount;

    }

    public void withdraw (double amount){
        if (this.balance - amount < 0){
            System.out.println("Something");
        }else {
            this.balance -= amount;
        }
    }

    @Override
    public String toString() {
        return "ID"+this.id;
    }
}
