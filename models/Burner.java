package models;

public class Burner {
    private Ramen ramen;
    private boolean isOn;

    public Burner() {
        this.ramen = new Ramen();
    }

    public boolean setRamen(Ramen ramen) {
        if (this.ramen == null) {
            this.ramen = ramen;
            return true;
        } else {
            return false;
        }
    }

    public Ramen getRamen() {
        return this.ramen;
    }

    public void toggleHeat() {
        if (this.isOn) {
            this.isOn = false;
        } else {
            this.isOn = true;
        }
    }
}
