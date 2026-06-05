package objectville;

public class ResourcePool {

    private int population;
    private int goods;
    private int lifestyle;

    public ResourcePool() {
        population = 0;
        goods      = 0;
        lifestyle  = 0;
    }

    public void addPopulation(int amount) { population += amount; }
    public void addGoods(int amount)      { goods += amount; }
    public void addLifestyle(int amount)  { lifestyle += amount; }

    public int getPopulation() { return population; }
    public int getGoods()      { return goods; }
    public int getLifestyle()  { return lifestyle; }

    public void reset() {
        population = 0;
        goods      = 0;
        lifestyle  = 0;
    }
}
