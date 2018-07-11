package p05StaticIDAndRate;

public class BankAccount {
    private int Id;
    private double Balance = 0.0;
    private final static Double DEFAULT_INTEREST = 0.02;
    private static Double rate = 0.02;

    public static void setInterestRate(double interest) {
        rate = interest;
    }

    public double getInterest(int years) {
        return this.Balance * rate * years;
    }

    public void setId(int id) {
        Id = id;
    }

    public void deposit(double amount) {
        this.Balance += amount;
    }

    public String toString() {
        return "ID" + this.Id;
    }

}
