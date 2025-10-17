import gui.Gui;
import models.*;

class AuntiesRameneria {
    public static void main(String[] args) {
        Order[] orders = {
            new Order().setRamen(new Ramen().giveRandomRamen()),
            new Order().setRamen(new Ramen().giveRandomRamen()),
            new Order().setRamen(new Ramen().giveRandomRamen()),
            new Order().setRamen(new Ramen().giveRandomRamen())
        };
        Gui gui = new Gui(orders);

        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(2000); // wait 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Order newOrder = new Order().setRamen(new Ramen().giveRandomRamen());
            gui.ordersPanel.addOrder(newOrder);
        }
    } 
}
