package models;

public class Bank {
    private double balance;
    
    public Bank() {
        balance = 0.0;
    }

    public void addAmount(double amount) {
        this.balance += amount; 
    }

    public void removeAmount(int amount) {
        this.balance -= amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public double calculatePay(Order order, long timeLeft, long time) {
        double timeMultiplier = 1.0; 
        double complexityMultiplier = 1.0;
        double survivalMultiplier = 1.0;

        int toppingSize = order.getRamen().getToppings().size();
        switch (toppingSize) {
            case 0:
                break;
            case 1:
                complexityMultiplier = 1.2;
            case 2:
                complexityMultiplier = 1.4;
            default:
                break;
        }
        
        if (timeLeft <= 10) {
            timeMultiplier = 1.2;
        } else if (timeLeft <= 20 && timeLeft > 10) {
            timeMultiplier = 1.4;
        } else {
            timeMultiplier = 1.6;
        }

        survivalMultiplier += time/100;
        System.out.println(String.format("Time Left multiplier %.1f", timeMultiplier));
        System.out.println(String.format("Difficult recipe multiplier %.1f", complexityMultiplier));
        System.out.println(String.format("Survival multiplier %.1f", survivalMultiplier));

        return 8.0 * timeMultiplier *  complexityMultiplier * survivalMultiplier;
    }
}
