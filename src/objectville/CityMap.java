package objectville;

//Arda

import java.util.ArrayList;

public class CityMap {

    private Cell[][] grid;
    private int rows;
    private int cols;

    private ArrayList<Zone> allZones;
    private ArrayList<HousingZone> houses;
    private ArrayList<IndustrialZone> industrials;
    private ArrayList<CommercialZone> commercials;
    private ArrayList<Building> buildings;

    public CityMap(Cell[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;

        allZones    = new ArrayList<>();
        houses      = new ArrayList<>();
        industrials = new ArrayList<>();
        commercials = new ArrayList<>();
        buildings   = new ArrayList<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid[r][c];
                if (cell instanceof HousingZone) {
                    houses.add((HousingZone) cell);
                    allZones.add((HousingZone) cell);
                } else if (cell instanceof IndustrialZone) {
                    industrials.add((IndustrialZone) cell);
                    allZones.add((IndustrialZone) cell);
                } else if (cell instanceof CommercialZone) {
                    commercials.add((CommercialZone) cell);
                    allZones.add((CommercialZone) cell);
                } else if (cell instanceof Building) {
                    buildings.add((Building) cell);
                }
            }
        }
    }

    public Cell[][] getGrid() { return grid; }
    public int getRows()      { return rows; }
    public int getCols()      { return cols; }
    public Cell getCell(int r, int c) { return grid[r][c]; }

    public ArrayList<Zone> getAllZones()             { return allZones; }
    public ArrayList<HousingZone> getHouses()        { return houses; }
    public ArrayList<IndustrialZone> getIndustrials(){ return industrials; }
    public ArrayList<CommercialZone> getCommercials() { return commercials; }
    public ArrayList<Building> getBuildings()         { return buildings; }
}
