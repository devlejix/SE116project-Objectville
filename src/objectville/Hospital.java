package objectville;

public class Hospital extends ServiceBuilding {
    public Hospital(int row, int col) { super(row, col, 3); }

    @Override public void applyServiceTo(Zone zone) { zone.giveHealth(); }
    @Override public boolean appliesToZone(Zone zone) { return zone instanceof HousingZone; }
    @Override public String getServiceName() { return "health"; }
}
