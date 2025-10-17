package models;

public class Order {
    private Ramen ramen = new Ramen();
    private Drink drink = new Drink();
    
    public Order setRamen(Ramen ramen) {
        this.ramen = ramen;
        return this;
    }

    public Order setDrink(Drink drink) {
        this.drink = drink;
        return this;
    }

    public Ramen getRamen() {
        return this.ramen;
    }

    public Drink getDrink() {
        return this.drink;
    }
    
}
