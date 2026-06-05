package objectville;

// Arda
public abstract class Zone extends Cell {

    protected int level;
    protected int electricity;
    protected int water;
    protected int internet;
    protected boolean security;
    protected boolean health;
    protected boolean education;
    protected int population;
    protected int goods;
    protected int lifestyle;
    protected int output;
    protected int utilityDemand;

    public Zone(int row, int col) {
        super(row, col);
        this.level = 0;
        this.utilityDemand = 1;
        this.output = 0;
    }

    @Override
    public boolean isConnectable() {
        return true;
    }

    public abstract void update();

    public int getLevel()         { return level; }
    public int getOutput()        { return output; }
    public int getUtilityDemand() { return utilityDemand; }
    public int getElectricity()   { return electricity; }
    public int getWater()         { return water; }
    public int getInternet()      { return internet; }

    public void deliverElectricity(int amount) {
        electricity += amount;
        System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " electricity");
    }

    public void deliverWater(int amount) {
        water += amount;
        System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " water");
    }

    public void deliverInternet(int amount) {
        internet += amount;
        System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " internet");
    }

    public void giveSecurity()  { security = true; }
    public void giveHealth()    { health = true; }
    public void giveEducation() { education = true; }

    public void deliverPopulation(int amount) {
        population += amount;
        if (amount > 0) {
            System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " population");
        }
    }

    public void deliverGoods(int amount) {
        goods += amount;
        if (amount > 0) {
            System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " goods");
        }
    }

    public void deliverLifestyle(int amount) {
        lifestyle += amount;
        if (amount > 0) {
            System.out.println(getDisplayName() + " at (" + row + "," + col + ") received " + amount + " lifestyle");
        }
    }

    public void endOfTick() {
        utilityDemand = Math.max(1, output);
        electricity = 0;
        water = 0;
        internet = 0;
        security = false;
        health = false;
        education = false;
        population = 0;
        goods = 0;
        lifestyle = 0;
    }

    public abstract String getDisplayName();
    public abstract String getResourceName();
}
