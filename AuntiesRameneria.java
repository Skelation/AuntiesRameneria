public class AuntiesRameneria {
    private Clock clock;

    private void run() {
        Stove stove = new Stove();
        Order[] orders = new Order[3];

        Clock clock = new Clock();

        clock.eventTimes.add(5L);

        clock.setListener(time -> {
            if (clock.eventTimes.contains(time)) {
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

    public static void main(String[] args) {
        new AuntiesRameneria().run();
    }
}
