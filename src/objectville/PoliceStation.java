package objectville;

public class PoliceStation extends ServiceBuilding {
    public PoliceStation(int row, int col) { super(row, col, 5); }

    @Override public void applyServiceTo(Zone zone) { zone.giveSecurity(); }
    @Override public boolean appliesToZone(Zone zone) { return true; }
    @Override public String getServiceName() { return "security"; }
}
