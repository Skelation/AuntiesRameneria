import gui.Gui;
import models.*;

class AuntiesRameneria {
    public static void main(String[] args) {
        Order[] orders = {
            new Order(),
            new Order()
        };
        new Gui(orders);
    } 
}
