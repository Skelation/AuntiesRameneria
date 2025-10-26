package models;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.LongConsumer;

public class Clock implements Runnable {
    private volatile boolean running = true;
    private long time = 0;
    private LongConsumer tickListener;
    public ConcurrentHashMap<Long, String> eventTimes = new ConcurrentHashMap<>();
    public ConcurrentHashMap<Long, Integer> difficultyTimes = new ConcurrentHashMap<>();
    public ConcurrentHashMap<Long, Integer> incomingOrderTimes = new ConcurrentHashMap<>();

    public Clock() {
        difficultyTimes.put(50L, 25);
        difficultyTimes.put(100L, 20);
        difficultyTimes.put(150L, 18);
        difficultyTimes.put(200L, 15);
        difficultyTimes.put(250L, 12);
        difficultyTimes.put(300L, 10);

        incomingOrderTimes.put(50L, 12000);
        incomingOrderTimes.put(100L, 10000);
        incomingOrderTimes.put(150L, 8000);
        incomingOrderTimes.put(200L, 6000);
        incomingOrderTimes.put(250L, 5000);
        incomingOrderTimes.put(300L, 4000);
    }

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
                eventTimes.remove(entry.getKey());
            }
        }
    }

    public void deleteCookingEvent(int n) {
        for (Map.Entry<Long, String> entry : eventTimes.entrySet()) {
            if (entry.getValue().contains("TimeDoneCooking " + String.valueOf(n))) {
                eventTimes.remove(entry.getKey());
            }
        }
    }

    public long getTimeEnd(int orderNumber) {
        for (Map.Entry<Long, String> entry : eventTimes.entrySet()) {
            if (entry.getValue().contains("TimeDoneOrder " + String.valueOf(orderNumber))) {
                return entry.getKey();
            } 
        }
        return 0;
    }

    public boolean hasBurnerEvent(int burnerIndex) {
        for (Map.Entry<Long, String> entry : eventTimes.entrySet()) {
            if (entry.getValue().contains("TimeDoneCooking " + String.valueOf(burnerIndex))) {
                return true;
            } 
        }
        return false;
    }

    public long getTimeCookEnd(int burnerIndex) {
        for (Map.Entry<Long, String> entry : eventTimes.entrySet()) {
            if (entry.getValue().contains("TimeDoneCooking " + String.valueOf(burnerIndex))) {
                return entry.getKey();
            } 
        }
        return 0;
    }
}
