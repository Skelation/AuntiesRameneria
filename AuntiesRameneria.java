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
        new Gui(orders);
    } 
}
