package objectville;
//Berkay
public abstract class UtilityProvider extends Building {

    public static final int CAPACITY = 100;

    public UtilityProvider(int row, int col) {
        super(row, col);
    }

    public int getCapacity() { return CAPACITY; }

    public abstract void deliverTo(Zone zone, int amount);
    public abstract int getDemandFrom(Zone zone);
}
