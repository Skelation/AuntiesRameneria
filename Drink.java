import java.util.*;

public class Drink {
    private String name;
    private String size;

    public String getName() {
        return this.name;
    }

    public String getSize() {
        return this.size;
    }

    public Drink giveRandomDrink() {
        String[] drinks = {"Soda", "Soju", "Kombucha", "Beer"};
        String[] sizes = {"Small", "Medium", "Large"};
        Drink randomDrink = new Drink();

        randomDrink.name = drinks[(new Random()).nextInt(drinks.length)];
        randomDrink.size = sizes[(new Random()).nextInt(sizes.length)];

        return randomDrink;
    }

}
