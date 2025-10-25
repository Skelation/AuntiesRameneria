package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ramen {
    private boolean isCooked;
    private boolean hasNoodle;
    private boolean hasWater;
    private int timeCooked;
    private boolean isBurnt;
    private String seasoning = "";
    private ArrayList<String> toppings = new ArrayList<>();

    public boolean isReadyToCook() {
        if (this.hasWater && this.hasNoodle) {
            return true;
        } else {
            return false;
        }
    }

    public boolean cook() {
        if (this.isCooked) {
            // Remove except if we want to implement it
            // this.isBurnt = true;
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

    public String getState(String[] images) {
        String path = "Assets/";
        String imagePath;
        if (this.hasWater && this.hasNoodle && this.isCooked) {
            if (this.toppings.size() == 0) {
                return path + "BowlRamen.png";
            } else {
                if (this.toppings.size() == 1) {
                    imagePath = path + "Just" + this.toppings.get(0) + ".png";
                    return imagePath;
                } else {
                    imagePath = path + this.toppings.get(0) + "And" + this.toppings.get(1) + ".png";
                    for (int i = 0; i < images.length; i++) {
                        if (imagePath.equals(path + images[i])) {
                            return imagePath;
                        }
                    }
                    imagePath = path + this.toppings.get(1) + "And" + this.toppings.get(0) + ".png";
                    return imagePath;
                }
            }
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

    public String getSeasoning() {
        return this.seasoning;
    }

    public void addTopping(String s) {
        this.toppings.add(s);
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
            Arrays.asList("Shiitake", "PorkLoin", "FriedEggs", "KaraAge",
                "Katsu", "Gyoza", "SpringOnion")
        );

        int numberOfToppings = (new Random()).nextInt(0, 3);
        
        for (int i = 0; i < numberOfToppings; i++) {
            int randomIndex = new Random().nextInt(allToppings.size());
            randomRamen.toppings.add(allToppings.get(randomIndex));
            allToppings.remove(randomIndex);
        }

        randomRamen.addWater();
        randomRamen.addNoodle();
        randomRamen.cook();
        randomRamen.addSeasoning(seasoning);

        return randomRamen;
    }

    public String getDescription() {
        String desc = "";
        desc += "Has Water: " +this.hasWater + "\n";
        desc += "Has Noodle: " +this.hasNoodle + "\n";
        desc += "CookedState: " + this.isCooked + "\n";
        desc += "BurnedState: " + this.isBurnt + "\n";
        desc += "Seasoning: " + this.seasoning + "\n";
        desc += "Cooking time: " + this.timeCooked + "\n";
        desc += "Topppings:\n";
        for (int i = 0; i < this.toppings.size(); i++) {
            desc += "   " + this.toppings.get(i) + "\n";
        }
        return desc;
    }

    public boolean matches(Ramen ramen) {
        if (!(this.hasWater == ramen.hasWater)) {
            return false;
        }

        if (!(this.hasNoodle == ramen.hasNoodle)) {
            return false;
        }

        if (!(this.isCooked == ramen.isCooked)) {
            return false;
        }
         
        if (!(this.isBurnt == ramen.isBurnt)) {
            return false;
        }

        if (!(this.seasoning.equals(ramen.seasoning))) {
            return false;
        }

        if (this.toppings.size() != ramen.toppings.size()) {
            return false;
        }
        
        for (int i = 0; i < this.toppings.size(); i++) {
            if (!ramen.toppings.contains(this.toppings.get(i))) {
                return false;
            }
        }
        return true;
    }
}
