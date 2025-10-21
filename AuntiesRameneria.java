import java.util.*;

import gui.Gui;
import models.*;

public class AuntiesRameneria {
    private Clock clock;
    int orderNumbers = 0;

    private void run() {
        // Stove stove = new Stove();
        ArrayList<Order> orders = new ArrayList<>();

        clock = new Clock();

        takeOrders(orders, clock);

        clock.eventTimes.put(5L, "check");

        clock.setListener(time -> {
            if (clock.eventTimes.keySet().contains(time)) {
                System.out.println("Lets go " + time); 
            }
        });

        Thread clockThread = new Thread(clock);
        clockThread.start();

        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(clock.getTime());
            } catch (InterruptedException err) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void takeOrders(ArrayList<Order> orders, Clock clock) {
        while (orders.size() < 3) {
            Order newOrder = (new Order()).newOrder();
            orderNumbers = orderNumbers + 1;
            newOrder.setOrderNumber(orderNumbers);
            orders.add(newOrder);
            clock.eventTimes.put(120L, String.format("TiemDoneOrder%d", orderNumbers));
        }
    }

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

        new AuntiesRameneria().run();
    }
}
