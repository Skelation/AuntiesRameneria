class Stove {
    private Burner[] burners;

    public boolean addRamen(Ramen ramen) {
        for (int i = 0; i < burners.length; i++) {
            if (burners[i].getRamen() == null) {
                burners[i].setRamen(ramen);
                return true;
            }
        }
        return false;
    }
}
