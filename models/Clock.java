package models;

import java.util.HashMap;
import java.util.Map;
import java.util.function.LongConsumer;

public class Clock implements Runnable {
    private volatile boolean running = true;
    private long time = 0;
    private LongConsumer tickListener;
    public HashMap<Long, String> eventTimes = new HashMap<>();

    public void setListener(LongConsumer listener) {
        this.tickListener = listener;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                time++;
                if (tickListener != null) tickListener.accept(time);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public long getTime() {
        return time;
    }

    public void stop() {
        running = false;
    }

    public void deleteEvent(int orderNumber) {
        for (Map.Entry<Long, String> entry : eventTimes.entrySet()) {
            if (entry.getValue().contains("TimeDoneOrder " + String.valueOf(orderNumber))) {
                System.out.println("Deleting order number " + orderNumber);
                eventTimes.remove(entry.getKey());
                System.out.println("Value of entry selected " + entry.getValue());
                System.out.println("All events " + eventTimes);
            } 
        }
    }
}
