package objectville;

//Arda
public abstract class Cell {

    protected int row;
    protected int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public boolean isConnectable() {
        return false;
    }
}