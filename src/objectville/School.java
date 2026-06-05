package objectville;

public class School extends ServiceBuilding {
    public School(int row, int col) { super(row, col, 4); }

    @Override public void applyServiceTo(Zone zone) { zone.giveEducation(); }
    @Override public boolean appliesToZone(Zone zone) { return zone instanceof HousingZone; }
    @Override public String getServiceName() { return "education"; }
}
