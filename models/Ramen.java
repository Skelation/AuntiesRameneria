package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ramen {
    private boolean isCooked;
    private boolean hasNoodle;
    private boolean hasWater;
    private boolean hasSeasoningOil;
    private int timeCooked;
    private boolean isBurnt;
    private String seasoning = "";
    private ArrayList<String> toppings = new ArrayList<>();

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

    public boolean addWater() {
        if (this.hasWater) {
            return false;
        } else {
            this.hasWater = true;
            return true;
        }
    }

    public String getState() {
        String path = "Assets/";
        if (this.hasWater && this.hasNoodle && this.isCooked) {
            return path + "BowlRamen.png";
        } else if (this.hasWater && this.hasNoodle) {
            return path + "BowlRawRamen.png";
        } else if (this.hasWater) {
            return path + "BowlWater.png";
        } else {
            return path + "EmptyBowl.png";
        }
    }

    public Ramen addSeasoning(String seasoning) {
        if (this.seasoning.isEmpty()) {
            this.seasoning = seasoning;
        }
        return this;
    }

    public boolean addSeasoningOil() {
        if (this.hasSeasoningOil) {
            return false;
        } else {
            this.hasSeasoningOil = true;
            return true;
        }
    }

    public String getSeasoning() {
        return this.seasoning;
    }

    public ArrayList<String> getToppings() {
        return this.toppings;
    }

    public Ramen giveRandomRamen() {
        Ramen randomRamen = new Ramen();
        String[] seasonings = {"Carbonara", "Hot Chicken", "2x Spicy",
            "3x Spicy", "Cheese", "Habanero Lime"};

        String seasoning = seasonings[(new Random()).nextInt(seasonings.length)];
        ArrayList<String> allToppings = new ArrayList<String>(
            Arrays.asList("Shiitake", "Pork loin", "Fried eggs", "KaraAge chicken",
                "Katsu chicken", "Gyoza", "Spring onions")
        );

        int numberOfToppings = (new Random()).nextInt(allToppings.size());
        
        for (int i = 0; i < numberOfToppings; i++) {
            randomRamen.toppings.add(allToppings.get((new Random()).nextInt(allToppings.size())));
        }

        randomRamen.addNoodle();
        randomRamen.cook();
        randomRamen.addSeasoning(seasoning);
        randomRamen.addSeasoningOil();

        return randomRamen;
    }
}
