package objectville;
//Berkay
public class WaterStation extends UtilityProvider {

    public WaterStation(int row, int col) { super(row, col); }

    @Override
    public void deliverTo(Zone zone, int amount) { zone.deliverWater(amount); }

    @Override
    public int getDemandFrom(Zone zone) {
        return Math.max(0, zone.getUtilityDemand() - zone.getWater());
    }
}
