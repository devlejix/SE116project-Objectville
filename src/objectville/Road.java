package objectville;
//Arda
public class Road extends Cell {

    public Road(int row, int col) {
        super(row, col);
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}
