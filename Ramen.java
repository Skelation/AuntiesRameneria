class Ramen {
    private boolean isCooked;
    private boolean hasNoodle;
    private boolean hasSpice;
    private boolean hasSoup;
    private boolean hasShiitake;
    private boolean hasSeasoningOil;
    private int timeCooked;
    private boolean isBurnt;

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
        if (this.hasNoodle== true) {
            return false;
        } else {
            this.hasNoodle = true;
            return true;
        }
    }

    public boolean addSoup() {
        if (this.hasSoup == true) {
            return false;
        } else {
            this.hasSoup = true;
            return true;
        }
    }

    public boolean addSpice() {
        if (this.hasSpice == true) {
            return false;
        } else {
            this.hasSpice = true;
            return true;
        }
    }

    public boolean addShiitake() {
        if (this.hasShiitake == true) {
            return false;
        } else {
            this.hasShiitake = true;
            return true;
        }
    }

    public boolean addSeasoningOil() {
        if (this.hasSeasoningOil == true) {
            return false;
        } else {
            this.hasSeasoningOil = true;
            return true;
        }
    }
}
