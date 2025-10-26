package gui;

import javax.swing.JButton;

import models.*;

// Class to check if ramen or drink has been added to order
class OrderButton extends JButton {
    // Ramen submitted to the order
    private Ramen userRamen;
    // Drink submitted to the order
    private Drink userDrink;
    // Order to compare to
    private Order order;

    public Ramen getUserRamen() {
        if (userRamen != null) {
            return userRamen;
        } else {
            return null; 
        }
    }

    public boolean hasRamen() {
        if (userRamen != null) {
            return true;
        } else {
            return false; 
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

    public boolean hasDrink() {
        if (userDrink != null) {
            return true;
        } else {
            return false; 
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
