package objectville;

import java.util.ArrayList;

public class Simulator {

    private CityMap map;
    private UtilityDistributor utilityDistributor;

    private int pendingPopulation;
    private int pendingGoods;
    private int pendingLifestyle;

    public Simulator(CityMap map) {
        this.map = map;
        this.utilityDistributor = new UtilityDistributor(map);
        pendingPopulation = 0;
        pendingGoods      = 0;
        pendingLifestyle  = 0;
    }

    public void run(int ticks) {
        for (int tick = 1; tick <= ticks; tick++) {
            System.out.println("Tick " + tick);
            runOneTick();
        }
    }

    private void runOneTick() {
        distributeServices();
        distributeUtilities();
        distributeResources();

        for (Zone zone : map.getAllZones()) {
            zone.update();
        }

        pendingPopulation = 0;
        pendingGoods      = 0;
        pendingLifestyle  = 0;

        for (HousingZone h : map.getHouses())        pendingPopulation += h.getOutput();
        for (IndustrialZone i : map.getIndustrials()) pendingGoods      += i.getOutput();
        for (CommercialZone c : map.getCommercials()) pendingLifestyle  += c.getOutput();

        for (Zone zone : map.getAllZones()) zone.endOfTick();
    }

    private void distributeServices() {
        for (Building b : map.getBuildings()) {
            if (b instanceof ServiceBuilding) {
                ServiceBuilding sb = (ServiceBuilding) b;
                for (Zone zone : map.getAllZones()) {
                    if (sb.isInRange(zone.getRow(), zone.getCol()) && sb.appliesToZone(zone)) {
                        sb.applyServiceTo(zone);
                        System.out.println(zone.getDisplayName() + " at ("
                                + zone.getRow() + "," + zone.getCol()
                                + ") received " + sb.getServiceName() + " service");
                    }
                }
            }
        }
    }

    private void distributeUtilities() {
        for (Building b : map.getBuildings()) {
            if (b instanceof UtilityProvider) {
                utilityDistributor.distribute((UtilityProvider) b);
            }
        }
    }

    private void distributeResources() {
        ArrayList<IndustrialZone> industrials = map.getIndustrials();
        ArrayList<CommercialZone> commercials = map.getCommercials();
        ArrayList<HousingZone>    houses      = map.getHouses();

        int popConsumers = industrials.size() + commercials.size();
        if (popConsumers > 0 && pendingPopulation > 0) {
            int perZone = pendingPopulation / popConsumers;
            for (IndustrialZone i : industrials) i.deliverPopulation(perZone);
            for (CommercialZone c : commercials) c.deliverPopulation(perZone);
        }

        if (!commercials.isEmpty() && pendingGoods > 0) {
            int perCommercial = pendingGoods / commercials.size();
            for (CommercialZone c : commercials) c.deliverGoods(perCommercial);
        }

        if (!houses.isEmpty() && pendingLifestyle > 0) {
            int perHouse = pendingLifestyle / houses.size();
            for (HousingZone h : houses) h.deliverLifestyle(perHouse);
        }
    }
}
