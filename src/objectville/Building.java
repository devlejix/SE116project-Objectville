package objectville;

//Berkay
public abstract class Building extends Cell {

    public Building(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isConnectable() {
        return false;
    }
}