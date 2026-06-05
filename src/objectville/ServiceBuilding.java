package objectville;

// Berkay
public abstract class ServiceBuilding extends Building {

    protected int radius;

    public ServiceBuilding(int row, int col, int radius) {
        super(row, col);
        this.radius = radius;
    }

    public boolean isInRange(int targetRow, int targetCol) {
        double distance = Math.sqrt(
                Math.pow(targetRow - this.row, 2) + Math.pow(targetCol - this.col, 2)
        );
        return distance <= radius;
    }

    public abstract void applyServiceTo(Zone zone);
    public abstract boolean appliesToZone(Zone zone);
    public abstract String getServiceName();
}
