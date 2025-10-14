import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class Ramen {
    private boolean isCooked;
    private boolean hasNoodle;
    private boolean hasSeasoningOil;
    private int timeCooked;
    private boolean isBurnt;
    private String seasoning;
    private ArrayList<String> toppings;

    public boolean cook() {
        if (this.isCooked) {
            this.isBurnt = true;
            return false;
        } else {
            this.isCooked = true;
            return true;
        }
    }

    public boolean addNoodle() {
        if (this.hasNoodle) {
            return false;
        } else {
            this.hasNoodle = true;
            return true;
        }
    }

    public void addSeasoning(String seasoning) {
        if (this.seasoning.isEmpty()) {
            this.seasoning = seasoning;
        }
    }

    public boolean addSeasoningOil() {
        if (this.hasSeasoningOil) {
            return false;
        } else {
            this.hasSeasoningOil = true;
            return true;
        }
    }

    public Ramen giveRandomRamen() {
        Ramen randomRamen = new Ramen();
        String[] seasonings = {"Carbonara", "Hot Chicken", "2x Spicy",
            "3x Spicy", "Cheese", "Habanero Lime"};

        String seasoning = seasonings[(new Random()).nextInt(seasonings.length)];
        ArrayList<String> allToppings = new ArrayList<String>(
            Arrays.asList("Shiitake", "Pork loin", "Fried eggs", "Karagae chicken",
                "Katsu chicken", "Gyoza", "Spring onions")
        );

        int numberOfToppings = (new Random()).nextInt(allToppings.size());
        
        for (int i = 0; i < numberOfToppings; i++) {
            randomRamen.toppings.add(toppings.get((new Random()).nextInt(toppings.size())));
        }

        randomRamen.addNoodle();
        randomRamen.cook();
        randomRamen.addSeasoning(seasoning);
        randomRamen.addSeasoningOil();

        return randomRamen;
    }
}
