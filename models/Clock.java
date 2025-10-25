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
