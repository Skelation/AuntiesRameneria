package gui;

import javax.swing.JButton;

import models.*;

class OrderButton extends JButton {
    private Ramen userRamen;
    private Drink userDrink;
    private Order order;

    public Ramen getUserRamen() {
        if (userRamen != null) {
            return userRamen;
        } else {
            return null; 
        }
    }

    public void setUserRamen(Ramen ramen) {
        this.userRamen = ramen;
    }

    public Drink getUserDrink() {
        if (userDrink != null) {
            return userDrink;
        } else {
            return null; 
        }
    }

    public void setUserDrink(Drink drink) {
        this.userDrink = drink;
    }

    public boolean isComplete() {
        if (this.userRamen != null && this.userDrink != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean orderMatches() {
        if (this.userRamen.matches(order.getRamen()) && this.userDrink.equals(order.getDrink().getName())) {
            return true;
        } else {
            return false;
        }
    }
}
