package objectville;

public class InternetHub extends UtilityProvider {

    public InternetHub(int row, int col) { super(row, col); }

    @Override
    public void deliverTo(Zone zone, int amount) { zone.deliverInternet(amount); }

    @Override
    public int getDemandFrom(Zone zone) {
        if (zone instanceof IndustrialZone) return 0;
        return Math.max(0, zone.getUtilityDemand() - zone.getInternet());
    }
}
