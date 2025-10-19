package models;

public class Stove {
    private Burner[] burners = new Burner[4];

    public boolean addRamen(Ramen ramen) {
        for (int i = 0; i < burners.length; i++) {
            if (burners[i].getRamen() == null) {
                burners[i].setRamen(ramen);
                return true;
            }
        }
        return false;
    }

    public Burner[] getBurners() {
        return this.burners;
    }
}
