package models;

public class Bank {
    private int balance;
    
    public Bank() {
        balance = 0;
    }

    public void addAmount(int amount) {
        this.balance += amount; 
    }

    public void removeAmount(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }
}
