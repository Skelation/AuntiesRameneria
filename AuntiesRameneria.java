import gui.Gui;
import models.*;

class AuntiesRameneria {
    public static void main(String[] args) {
        Order[] orders = {
            new Order().setRamen(new Ramen().giveRandomRamen()),
            new Order().setRamen(new Ramen().giveRandomRamen()),
            new Order().setRamen(new Ramen().giveRandomRamen()),
        };
        Stove stove = new Stove();
        Gui gui = new Gui(orders, stove);

        // for (int i = 0; i < 3 && orders.length < 3; i++) {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order newOrder = new Order().setRamen(new Ramen().giveRandomRamen());
            gui.ordersPanel.addOrder(newOrder);
        }
    } 
}
