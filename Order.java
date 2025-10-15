import java.util.Random;

public class Order {
    private Ramen ramen = new Ramen();
    private Drink drink = new Drink();
    
    public void setRamen(Ramen ramen) {
        this.ramen = ramen;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public Ramen getRamen() {
        return this.ramen;
    }

    public Drink getDrink() {
        return this.drink;
    }

    public Order newOrder() {
        Order order = new Order();
        order.drink = new Drink().giveRandomDrink();
        order.ramen = new Ramen().giveRandomRamen();
        return order;
    }
}
