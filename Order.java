import java.util.Random;

public class Order {
    private Ramen ramen = new Ramen();
    private Drink drink = new Drink();
    private int orderNumber = 0;
    
    public void setRamen(Ramen ramen) {
        this.ramen = ramen;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Ramen getRamen() {
        return this.ramen;
    }

    public Drink getDrink() {
        return this.drink;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public Order newOrder() {
        Order order = new Order();
        order.drink = new Drink().giveRandomDrink();
        order.ramen = new Ramen().giveRandomRamen();
        return order;
    }
}
