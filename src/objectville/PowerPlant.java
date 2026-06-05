package objectville;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(int row, int col) { super(row, col); }

    @Override
    public void deliverTo(Zone zone, int amount) { zone.deliverElectricity(amount); }

    @Override
    public int getDemandFrom(Zone zone) {
        return Math.max(0, zone.getUtilityDemand() - zone.getElectricity());
    }
}
