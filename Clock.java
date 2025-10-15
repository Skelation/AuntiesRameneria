import java.util.HashSet;
import java.util.Set;
import java.util.function.LongConsumer;

public class Clock implements Runnable {
    private volatile boolean running = true;
    private long time = 0;
    private LongConsumer tickListener;
    public Set<Long> eventTimes = new HashSet<>();

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
}

