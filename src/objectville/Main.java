package objectville;

// Mehmet
public class Main {

    public static void main(String[] args) {
        String mapFile;
        int ticks;

        if (args.length >= 2) {
            mapFile = args[0];
            try {
                ticks = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new InvalidCityMapException("Tick sayisi sayi olmali: " + args[1]);
            }
        } else {
            mapFile = "mymap.txt";
            ticks = 10;
            System.out.println("Arguman verilmedi, varsayilan: " + mapFile + ", " + ticks + " tick");
        }

        if (ticks < 1) throw new InvalidCityMapException("Tick en az 1 olmali");

        CityMap map = MapLoader.load(mapFile);
        Simulator sim = new Simulator(map);
        sim.run(ticks);
    }
}
