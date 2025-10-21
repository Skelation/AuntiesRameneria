import java.util.*;

import gui.Gui;
import models.*;

public class AuntiesRameneria {
    private Clock clock;
    private boolean running = false;
    private int orderNumbers = 0;
    public ArrayList<Order> orders;
    private Gui gui;
    private Stove stove;

    private void run() {
        stove = new Stove();
        orders = new ArrayList<>();
        Order[] initialOrders = {};
        clock = new Clock();

        gui = new Gui(initialOrders, stove);

        startClock();

        Thread orderThread = new Thread(() -> {
            takeOrders(orders, clock);
        });
        orderThread.start();
    }

    private void startClock() {
        // if (running) {
        //     return;
        // }
        
        running = true;
        
        clock.setListener(time -> {
            if (clock.eventTimes.keySet().contains(time)) {
                System.out.println("Event triggered: " + time); 
            }
        });

        Thread clockThread = new Thread(clock);
        clockThread.start();

        Thread timeThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(1010);
                    System.out.println(clock.getTime());
                } catch (InterruptedException err) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        timeThread.start();
    }

    public void stopClock() {
        running = false;
    }

    public Clock getClock() {
        return clock;
    }

    public long getTime() {
        return clock.getTime();
    }

    public void addEvent(long time, String event) {
        clock.eventTimes.put(time, event);
    }

    public void takeOrders(ArrayList<Order> orders, Clock clock) {
        while (orders.size() < 3) {
            Order newOrder = (new Order()).newOrder();
            orderNumbers = orderNumbers + 1;
            System.out.println("New Order " + orderNumbers);
            newOrder.setOrderNumber(orderNumbers);
            orders.add(newOrder);
            clock.eventTimes.put(120L, String.format("TimeDoneOrder%d", orderNumbers));
        }
    }

    public static void main(String[] args) {
        new AuntiesRameneria().run();
    }
}
