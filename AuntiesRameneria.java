import java.util.*;

import javax.swing.SwingUtilities;

import gui.Gui;
import models.*;

public class AuntiesRameneria {
    private Clock clock;
    private boolean running = false;
    private int orderNumbers = 0;
    public ArrayList<Order> orders;
    private Gui gui;
    private Stove stove;
    private Bank bank;
    private int timeToFinishOrder = 30;
    private int incomingOrderTime = 15000;

    private void run() {
        stove = new Stove();
        orders = new ArrayList<>();
        clock = new Clock();
        bank = new Bank();

        gui = new Gui(orders, stove, bank, clock);

        startClock();

        Thread orderThread = new Thread(() -> {
            takeOrders(orders, clock);
        });
        orderThread.start();
    }

    

    private void startClock() {
        running = true;
        
        clock.setListener(time -> {
            if (clock.difficultyTimes.keySet().contains(time)) {
                timeToFinishOrder = clock.difficultyTimes.get(time);
            }
            if (clock.incomingOrderTimes.keySet().contains(time)) {
                incomingOrderTime= clock.incomingOrderTimes.get(time);
            }
            if (clock.eventTimes.keySet().contains(time)) {
                String event = clock.eventTimes.get(time);
                String[] eventArray = event.split(" ");

                if (eventArray[0].equals("TimeDoneOrder")) {
                    bank.removeAmount(10);
                    gui.ordersPanel.balanceLabel.setText(String.format("$: %.2f", bank.getBalance()));
                    gui.ordersPanel.moneyPanel.revalidate();
                    gui.ordersPanel.moneyPanel.repaint();

                    gui.ordersPanel.removeOrder(Integer.valueOf(eventArray[1]));
                    gui.counterPanel.removeClient(Integer.valueOf(eventArray[1]));
                } else if (eventArray[0].equals("TimeDoneCooking")) {
                    int burnerIndex = Integer.valueOf(eventArray[1]);
                    Ramen selectedRamen = gui.kitchenPanel.getStove().getBurners()[burnerIndex].getRamen();
                    selectedRamen.cook();
                    clock.deleteCookingEvent(burnerIndex);
                    gui.kitchenPanel.updateBurnerImages();
                }
            }
        });

        Thread clockThread = new Thread(clock);
        clockThread.start();

        Thread timeThread = new Thread(() -> {
            while (running) {
                try {
                    Thread.sleep(300);
                    gui.ordersPanel.updateButtonTimers();
                    gui.kitchenPanel.updateKitchenTimers();
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
        while (true) {
            synchronized (orders) {
                if (orders.size() < 3) {
                    Order newOrder = (new Order()).newOrder();
                    orderNumbers++;
                    newOrder.setOrderNumber(orderNumbers);
                    orders.add(newOrder);
                    clock.eventTimes.put(clock.getTime() + timeToFinishOrder, String.format("TimeDoneOrder %d", orderNumbers));

                    SwingUtilities.invokeLater(() -> {
                        gui.counterPanel.addClient(orderNumbers);
                        gui.ordersPanel.addOrder(newOrder, bank);
                    });
                }
            }

            try {
                Thread.sleep(incomingOrderTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) {
        new AuntiesRameneria().run();
    }
}
