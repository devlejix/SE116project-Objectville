package objectville;

//Arda
public class HousingZone extends Zone {

    public HousingZone(int row, int col) {
        super(row, col);
    }

    @Override
    public void update() {
        boolean hasElec  = electricity > 0;
        boolean hasWater = water > 0;
        boolean hasNet   = internet > 0;

        int oldLevel = level;

        if (!hasElec || !hasWater || !hasNet) {
            level  = 0;
            output = 0;
            printMessages(oldLevel);
            return;
        }

        int m = Math.min(electricity, Math.min(water, internet));

        boolean canLevel2 = security && health && education;
        boolean canLevel3 = canLevel2 && lifestyle > 0;

        int targetLevel;
        if      (canLevel3) targetLevel = 3;
        else if (canLevel2) targetLevel = 2;
        else                targetLevel = 1;

        if (targetLevel > level)      level = level + 1;
        else if (targetLevel < level) level = level - 1;

        if      (level == 0) output = 0;
        else if (level == 1) output = m;
        else if (level == 2) output = 2 * m;
        else                 output = 2 * m + lifestyle;

        printMessages(oldLevel);
    }

    private void printMessages(int oldLevel) {
        System.out.println(getDisplayName() + " at (" + row + "," + col + ") generated " + output + " " + getResourceName());
        if (level > oldLevel)
            System.out.println(getDisplayName() + " at (" + row + "," + col + ") levels up from " + oldLevel + " to " + level);
        else if (level < oldLevel)
            System.out.println(getDisplayName() + " at (" + row + "," + col + ") levels down from " + oldLevel + " to " + level);
    }

    @Override public String getDisplayName()  { return "House"; }
    @Override public String getResourceName() { return "population"; }
}
